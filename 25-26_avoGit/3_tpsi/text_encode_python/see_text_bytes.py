# This is a sample Python script.

# Press ⌃R to execute it or replace it with your code.
# Press Double ⇧ to search everywhere for classes, files, tool windows, actions, and settings.

# Press the green button in the gutter to run the script.
if __name__ == '__main__':
    text_utf8 = "PIPPO"
    array = bytearray(text_utf8, "utf-8")
    print("########codifica utf8 di ", text_utf8)
    for el in array:
        hex_code = hex(el).replace('0x', '')
        bin_code = bin(el)
        print("carattere", bytes.fromhex(hex_code).decode("utf-8"))
        print("valore esadecimale", hex_code)
        print("valore binario", bin_code)
        print("valore decimale", el)
    test_ascii = "A"
    array = bytearray(test_ascii, "latin-1")
    print("########codifica ASCII di ", test_ascii)
    # print(array)
    for el in array:
        hex_code = hex(el).replace('0x', '')
        bin_code = bin(el)
        print("carattere", bytes.fromhex(hex_code).decode("latin-1"))
        print("valore esadecimale", hex_code)
        print("valore binario", bin_code)
        print("valore decimale", el)
    test_utf16 = "A"

    print(test_utf16)
    # array = bytearray(test_utf16, "utf-16")
    # print("########codifica utf16 di ", test_utf16)
    # # print(array)
    # two_bytes = 0
    # hex_code = ''
    # bin_code = ''
    # for el in array:
    #     if two_bytes < 2:
    #         hex_code = hex_code + hex(el).replace('0x', '')
    #         bin_code += bin(el)
    #         two_bytes += 1
    #     if two_bytes == 2:
    #         two_bytes = 0
    #         print("carattere", bytes.fromhex(hex_code).decode("utf-16"))
    #         print("valore esadecimale", hex_code)
    #         print("valore binario", bin_code)
    #         print("valore decimale", el)





# See PyCharm help at https://www.jetbrains.com/help/pycharm/
