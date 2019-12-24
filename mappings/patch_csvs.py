import csv
import os
import shutil
import sys

entries = {
    'fields.csv': [],
    'methods.csv': [],
    'params.csv': []
}

for key in entries.keys():
    mcp_file = 'mcp/{}'.format(key)
    patch_file = 'custom/{}'.format(key)

    if not os.path.isfile(patch_file):
        print('Copying {}...'.format(key))
        shutil.copyfile(mcp_file, key)
        continue

    print('Patching {}...'.format(key))

    header_row = []

    # Read custom csv
    with open(patch_file, 'r') as f:
        reader = csv.reader(f)
        for row in reader:
            name = row[0]

            if name.startswith('searge') or name.startswith('param'):
                header_row = row
                continue

            entries[key].append(row)

    # Read MCP csv and merge it
    with open(mcp_file, 'r') as f:
        reader = csv.reader(f)
        for row in reader:
            name = row[0]
            existing = False

            if name.startswith('searge') or name.startswith('param'):
                continue

            for existing_entry in entries[key]:
                if name == existing_entry[0]:
                    existing = True
                    break

            if not existing:
                entries[key].append(row)

    entries[key].sort(key=lambda x: x[0])

    # Insert header at the start of the list
    entries[key].insert(0, header_row)

    # Write final output file
    with open(key, 'w') as f:
        writer = csv.writer(f, lineterminator='\n')

        for row in entries[key]:
            writer.writerow(row)

    print('Created {}'.format(key))