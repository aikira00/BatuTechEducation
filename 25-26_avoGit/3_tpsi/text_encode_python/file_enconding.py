def read_codes(text, code):
    array = bytearray(text, code)
    for el in array:
        hex_code = hex(el).replace('0x', '')
        bin_code = bin(el)
        if code != "utf-8":
            print("carattere", bytes.fromhex(hex_code).decode(code))
            print("valore esadecimale", hex_code)
            print("valore binario", bin_code)
            print("valore decimale", el)


with open("ascii_encoding.txt", "w", encoding="latin-1") as f_out:
    f_out.write("a à ò")

with open("ascii_encoding.txt", "r", encoding="latin-1") as f_in:
    for line in f_in.readlines():
        print(line)
        read_codes(line, "latin-1")

with open("ascii_encoding.txt", "r", encoding="utf-8") as f_in:
    try:
        for line in f_in.readlines():
            print(line)
    except UnicodeError as e:
        print(e)

with open("utf8_encoding.txt", "w", encoding="utf-8") as f_out:
    f_out.write("a à ò")

with open("utf8_encoding.txt", "r", encoding="utf-8") as f_in:
    for line in f_in.readlines():
        print(line)
        read_codes(line, "utf-8")

with open("utf8_encoding.txt", "r", encoding="latin-1") as f_in:

    for line in f_in.readlines():
        print(line)


with open("utf16_encoding.txt", "w", encoding="utf-16") as f_out:
    f_out.write("a à ò")

with open("utf16_encoding.txt", "r", encoding="utf-16") as f_in:
    for line in f_in.readlines():
        print(line)
        # read_codes(line, "utf-16")


