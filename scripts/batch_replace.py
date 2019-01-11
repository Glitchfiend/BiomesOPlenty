import os
import pathlib
import sys

start_dir = sys.argv[1]
#orig_name = sys.argv[2]
#new_name = sys.argv[3]

new_woods = [ 'cherry', 'mahogany', 'jacaranda', 'palm', 'willow', 'dead', 'magic', 'umbran', 'hellbark',
              'ethereal']

orig_name = 'fir'
print('Searching for files in {}'.format(start_dir))

paths_to_process = []

# Walk *before* copying files or else we get unnecessary extra work
for root, dirs, filenames in os.walk(start_dir):
    for filename in filenames:
        if not orig_name in filename:
            continue
        paths_to_process.append(pathlib.Path(root, filename))

for in_file_path in paths_to_process:
    for new_name in new_woods:
        out_file_path = pathlib.Path(in_file_path.parent, in_file_path.name.replace(orig_name, new_name))
        print('Copying from {} to {}'.format(in_file_path, out_file_path))

        with open(in_file_path, 'rt') as fin:
            with open(out_file_path, 'wt') as fout:
                for line in fin:
                    fout.write(line.replace(orig_name, new_name))