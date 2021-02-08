import json

lists = [1, 2, 3, 4]
filename = 'numbers.json'
with open(filename, 'w') as json_file:
    json.dump(lists, json_file)

with open(filename) as json_file:
    numbers = json_file.read()
    print(numbers)