arch = open("input.txt")


# FIRST PART

def getFirstDigit(line):
    for character in line:
        if(str(character).isdigit()):
            return character

sum = 0
num1 = ""
num2 = ""
iterations = 0
for line in arch:
    reveredLine = ''.join(reversed(line))
    num1 = getFirstDigit(line)
    num2 = getFirstDigit(reveredLine)
    num = num1 + num2
    sum = sum + int(num)
    iterations += 1
print("First result: " + str(sum))
print("Iterations: " + str(iterations))


# SECOND PART

def getFirstDigitV2(line, reversed):
    i = 0
    for character in line:
        if(str(character).isdigit()):
            return character
        if(not(reversed)):
            if(line[i:i+3] == "one"):
                return "1"
            if(line[i:i+3] == "two"):
                return "2"
            if(line[i:i+5] == "three"):
                return "3"
            if(line[i:i+4] == "four"):
                return "4"
            if(line[i:i+4] == "five"):
                return "5"
            if(line[i:i+3] == "six"):
                return "6"
            if(line[i:i+5] == "seven"):
                return "7"
            if(line[i:i+5] == "eight"):
                return "8"
            if(line[i:i+4] == "nine"):
                return "9"
        else:
            if(line[i:i+3] == "eno"):
                return "1"
            if(line[i:i+3] == "owt"):
                return "2"
            if(line[i:i+5] == "eerht"):
                return "3"
            if(line[i:i+4] == "ruof"):
                return "4"
            if(line[i:i+4] == "evif"):
                return "5"
            if(line[i:i+3] == "xis"):
                return "6"
            if(line[i:i+5] == "neves"):
                return "7"
            if(line[i:i+5] == "thgie"):
                return "8"
            if(line[i:i+4] == "enin"):
                return "9"
        i += 1


arch.seek(0)
sum = 0
num1 = ""
num2 = ""
iterations = 0
for line in arch:
    reveredLine = ''.join(reversed(line))
    num1 = getFirstDigitV2(line, False)
    num2 = getFirstDigitV2(reveredLine, True)
    num = num1 + num2
    sum = sum + int(num)
    iterations += 1
print("Second result: " + str(sum))
print("Iterations: " + str(iterations))