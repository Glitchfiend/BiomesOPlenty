import argparse
import re
from pathlib import Path

BASE_PATH = 'biomesoplenty'
WORLDGEN_PATH = f'{BASE_PATH}/common/worldgen'
FEATURE_PATH = f'{WORLDGEN_PATH}/feature'
PLACEMENT_PATH = f'{WORLDGEN_PATH}/placement'

VANILLA_BLOCKS_CLASS = 'Blocks'
BOP_BLOCKS_CLASS = 'BOPBlocks'

ALLOWED_FEATURE_SOURCES = [ 'bop', 'vanilla' ]

VANILLA_ALLOWED_LOGS = [ 'oak_log', 'spruce_log', 'birch_log', 'jungle_log', 'acacia_log', 'dark_oak_log' ]
BOP_ALLOWED_LOGS = [ 'fir_log', 'redwood_log', 'cherry_log', 'mahogany_log', 'jacaranda_log', 'palm_log', 'willow_log', 'dead_log', 'magic_log', 'umbran_log', 'hellbark_log' ]
ALLOWED_LOGS = VANILLA_ALLOWED_LOGS + BOP_ALLOWED_LOGS

VANILLA_ALLOWED_LEAVES = [ 'oak_leaves', 'spruce_leaves', 'birch_leaves', 'jungle_leaves', 'acacia_leaves', 'dark_oak_leaves' ]
BOP_ALLOWED_LEAVES = [ 'origin_leaves', 'flowering_oak_leaves', 'rainbow_birch_leaves', 'yellow_autumn_leaves', 'orange_autumn_leaves', 'maple_leaves', 'fir_leaves', 'redwood_leaves', 'white_cherry_leaves', 'pink_cherry_leaves', 'mahogany_leaves', 'jacaranda_leaves', 'palm_leaves', 'willow_leaves', 'dead_leaves', 'magic_leaves', 'umbran_leaves', 'hellbark_leaves' ]
ALLOWED_LEAVES = VANILLA_ALLOWED_LEAVES + BOP_ALLOWED_LEAVES

VANILLA_SAPLINGS = [ 'oak_sapling', 'spruce_sapling', 'birch_sapling', 'jungle_sapling', 'acacia_sapling', 'dark_oak_sapling' ]

VANILLA_BLOCKS = set(VANILLA_ALLOWED_LOGS + VANILLA_ALLOWED_LEAVES + VANILLA_SAPLINGS)

FEATURE_PATTERN = re.compile('ConfiguredFeature<.*> (.*) = register\(".*", ([a-zA-Z]+)\.([A-Z_]+)\.configured.*')
PLACEMENT_PATTERN = re.compile('public static final PlacedFeature (.*) = register\(".*", .*')

class FeatureType:
    def __init__(self, name, source, type, line):
        self.name = name.lower()

        if source == 'BOPBaseFeatures':
            source = 'bop'
        elif source == 'Feature':
            source = 'vanilla'            

        self.source = source.lower()
        self.type = type.lower()
        self.line = line

class PlacementType:
    def __init__(self, name, line):
        self.name = name.lower()
        self.line = line

def filter_features(source, type):
    return sorted(filter(lambda feature: feature.source == source and feature.type == type, features), key=lambda feature: feature.name)

def write_with_prologue_epilogue(path, pattern, code):
    with open(path, 'r') as f:
        lines = f.readlines()

    prologue = lines[:lines.index('{\n')+1]

    # Find the epilogue
    for line in lines:
        if re.search(pattern, line) is not None:
            epilogue = lines[lines.index(line)+1:]

    output = ''.join(prologue) + code + ''.join(epilogue)
    
    with open(path, 'w') as f:
        f.write(output)

def generate_section(comment, features, end_newline=True):
    code = f'    // {comment}\n'
    for feature in features:
        code += f'    {feature.line}\n'
    if end_newline:
        code += '\n'
    return code

def parse_registration(file_path, pattern, type):
    with open(file_path, 'r') as f:
        lines = f.readlines()

    registrations = []
    for line in lines:
        m = re.search(pattern, line)
        if m is not None:
            registrations.append(type(*m.groups(), line.strip()))
    return registrations

def convert_feature_source(source):
    if source == 'vanilla':
        return 'Feature'
    else:
        return 'BOPBaseFeatures'

def get_block_with_class(block):
    namespace = block.split(':')[0]

    if ':' in block:
        block = block.split(':')[1]

    if block in VANILLA_BLOCKS or namespace == 'minecraft':
        blocks_class = VANILLA_BLOCKS_CLASS
    else:
        blocks_class = BOP_BLOCKS_CLASS

    return f'{blocks_class}.{block.upper()}'

def create_state_provider(block):
    if block is None:
        return None
    return f'BlockStateProvider.simple({get_block_with_class(block)})'

def create_builder_call(func, val):
    if val is None:
        return ''
    return f'.{func}({val})'

#######################################
# Tree features
#######################################

def write_tree_features(file_path, features):
    basic_trees = filter_features('bop', 'basic_tree')
    big_trees = filter_features('bop', 'big_tree')
    taiga_trees = filter_features('bop', 'taiga_tree')
    bush_trees = filter_features('bop', 'bush_tree')
    poplar_trees = filter_features('bop', 'poplar_tree')
    cypress_trees = filter_features('bop', 'cypress_tree')
    twiglet_trees = filter_features('bop', 'twiglet_tree')
    special_trees = set(features) - set(basic_trees + big_trees + taiga_trees + bush_trees + poplar_trees + cypress_trees + twiglet_trees)

    code = generate_section('Standard trees', basic_trees)
    code += generate_section('Big trees', big_trees)
    code += generate_section('Conifer trees', taiga_trees)
    code += generate_section('Poplar trees', poplar_trees)
    code += generate_section('Swamp trees', cypress_trees)
    code += generate_section('Bush trees', bush_trees)
    code += generate_section('Twiglets', twiglet_trees)
    code += generate_section('Special trees', special_trees, False)

    write_with_prologue_epilogue(file_path, FEATURE_PATTERN, code)

def create_tree_feature(args):
    configuration = ''

    if args.type == 'big_tree':
        configuration += 'new BigTreeConfiguration.Builder()'
    elif args.type == 'bush_tree':
        configuration += 'new BasicTreeConfiguration.Builder()'
    elif args.type == 'cypress_tree':
        configuration += 'new CypressTreeConfiguration.Builder()'
    elif args.type == 'poplar_tree':
        configuration += 'new PoplarTreeConfiguration.Builder()'
    elif args.type == 'twiglet_tree':
        configuration += 'new TwigletTreeConfiguration.Builder()'

    configuration += create_builder_call('trunk', create_state_provider(args.trunk))
    configuration += create_builder_call('foliage', create_state_provider(args.foliage))
    configuration += create_builder_call('vine', create_state_provider(args.vine))
    configuration += create_builder_call('hanging', create_state_provider(args.hanging))
    configuration += create_builder_call('altFoliage', create_state_provider(args.alt_foliage))
    configuration += create_builder_call('trunkFruit', create_state_provider(args.trunk_fruit))
    configuration += create_builder_call('minHeight', args.min_height)
    configuration += create_builder_call('maxHeight', args.max_height)

    if args.type == 'big_tree':
        configuration += create_builder_call('trunkWidth', args.trunk_width)
        configuration += create_builder_call('foliageHeight', args.foliage_height)

        if args.foliage_density is not None:
            configuration += create_builder_call('foliageDensity', f'{args.foliage_density}D')
    elif args.type == 'cypress_tree':
        configuration += create_builder_call('trunkWidth', args.trunk_width)
    elif args.type == 'twiglet_tree' and args.leaf_chance_even is not None and args.leaf_chance_odd is not None:
        configuration += create_builder_call('leafChance', f'{args.leaf_chance_even}F, {args.leaf_chance_odd}F')

    configuration += '.build()'

    line = f'public static final ConfiguredFeature<TreeConfiguration, ?> {args.name.upper()} = register("{args.name.lower()}", {convert_feature_source(args.source)}.{args.type.upper()}.configured({configuration}));'
    return FeatureType(args.name, args.source, args.type, line)

def write_tree_placements(file_path, placements):
    placements.sort(key=lambda placement: placement.name)

    code = ''
    for placement in placements:
        code += f'    {placement.line}\n'

    write_with_prologue_epilogue(file_path, PLACEMENT_PATTERN, code)

def create_tree_placement(args):
    line = f'public static final PlacedFeature {args.name.upper()}_CHECKED = register("{args.name.lower()}", BOPTreeFeatures.{args.name.upper()}.filteredByBlockSurvival({get_block_with_class(args.survival_filter_block)}));' 
    return PlacementType(args.name, line)

def add_common_tree_subparser_args(parser):
    parser.add_argument('--trunk', dest='trunk', choices=ALLOWED_LOGS)
    parser.add_argument('--foliage', dest='foliage', choices=ALLOWED_LEAVES)
    parser.add_argument('--vine', dest='vine')
    parser.add_argument('--hanging', dest='hanging')
    parser.add_argument('--trunk_fruit', dest='trunk_fruit')
    parser.add_argument('--alt_foliage', dest='alt_foliage', choices=ALLOWED_LEAVES)
    parser.add_argument('--min_height', dest='min_height', type=int)
    parser.add_argument('--max_height', dest='max_height', type=int)

def add_tree_subparser_args(parser):
    parser.add_argument('name')
    parser.add_argument('source', choices=ALLOWED_FEATURE_SOURCES)
    parser.add_argument('survival_filter_block')
    subparsers = parser.add_subparsers(dest='type', required=True)

    big_tree_parser = subparsers.add_parser('big_tree')
    big_tree_parser.add_argument('--trunk_width', dest='trunk_width', type=int)
    big_tree_parser.add_argument('--foliage_height', dest='foliage_height', type=int)
    big_tree_parser.add_argument('--foliage_density', dest='foliage_density', type=float)
    add_common_tree_subparser_args(big_tree_parser)

    bush_tree_parser = subparsers.add_parser('bush_tree')
    add_common_tree_subparser_args(bush_tree_parser)

    cypress_tree_parser = subparsers.add_parser('cypress_tree')
    cypress_tree_parser.add_argument('--trunk_width', dest='trunk_width', type=int)
    add_common_tree_subparser_args(cypress_tree_parser)

    poplar_tree_parser = subparsers.add_parser('poplar_tree')
    add_common_tree_subparser_args(poplar_tree_parser)

    twiglet_tree_parser = subparsers.add_parser('twiglet_tree')
    twiglet_tree_parser.add_argument('--leaf_chance_even', dest='leaf_chance_even', type=float)
    twiglet_tree_parser.add_argument('--leaf_chance_odd', dest='leaf_chance_odd', type=float)
    add_common_tree_subparser_args(twiglet_tree_parser)

def add_tree_selector_subparser_args(parser):
    parser.add_argument('name')
    parser.add_argument('count', type=int)

if __name__ == "__main__":
    parser = argparse.ArgumentParser(description='Generates world generation code for Minecraft 1.18.')

    parser.add_argument('src_path', type=Path)
    subparsers = parser.add_subparsers(dest='mode', required=True)

    add_tree_subparser_args(subparsers.add_parser('add_tree'))
    add_tree_selector_subparser_args(subparsers.add_parser('add_tree_selector'))

    args = parser.parse_args()
    src = args.src_path

    if args.mode == 'add_tree':
        feature_file_path = f'{src}/{FEATURE_PATH}/BOPTreeFeatures.java'
        features = parse_registration(feature_file_path, FEATURE_PATTERN, FeatureType)
        features.append(create_tree_feature(args))
        write_tree_features(feature_file_path, features)

        placement_file_path = f'{src}/{PLACEMENT_PATH}/BOPTreePlacements.java'
        placements = parse_registration(placement_file_path, PLACEMENT_PATTERN, PlacementType)
        placements.append(create_tree_placement(args))
        write_tree_placements(placement_file_path, placements)
    elif args.mode == 'add_tree_selector':
        feature_file_path = f'{src}/{FEATURE_PATH}/BOPVegetationFeatures.java'
        features = parse_registration(feature_file_path, FEATURE_PATTERN, FeatureType)
        # features = filter()
