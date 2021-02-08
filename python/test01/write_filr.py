with open('file.txt', 'w') as txt_file:
    txt_file.write("100")


with open('file.txt', 'a') as txt_file:
    """111111"""
    txt_file.write('300')