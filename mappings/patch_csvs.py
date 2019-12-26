import csv
import hashlib
import json
import os
import shutil
import sys

def sha256sum(filename):
    h  = hashlib.sha256()
    b  = bytearray(128*1024)
    mv = memoryview(b)
    with open(filename, 'rb', buffering=0) as f:
        for n in iter(lambda : f.readinto(mv), 0):
            h.update(mv[:n])
    return h.hexdigest()

entries = {
    'fields.csv': [],
    'methods.csv': [],
    'params.csv': []
}

hashes = {}

if os.path.isfile('last_updated.json'):
    with open('last_updated.json', 'r') as f:
        hashes = json.load(f)

for key in entries.keys():
    mcp_file = 'mcp/{}'.format(key)
    patch_file = 'custom/{}'.format(key)

    if not os.path.isfile(patch_file):
        print('Copying {}...'.format(key))
        shutil.copyfile(mcp_file, key)
        continue

    # Check for changes in the patch file
    hash = sha256sum(patch_file)

    if os.path.isfile(key) and key in hashes.keys() and hashes[key] == hash:
        print('No changes found in {}'.format(patch_file))
        continue
    else:
        hashes[key] = hash

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

# Save hashes
with open('last_updated.json', 'w') as f:
    json.dump(hashes, f)