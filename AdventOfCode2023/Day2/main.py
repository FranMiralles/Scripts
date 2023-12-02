arch = open("input.txt")

RED_CUBES = 12
GREEN_CUBES = 13
BLUE_CUBES = 14


# FIRST PART

sum = 0
for line in arch:
    split1 = line.split(": ")
    id = int(split1[0][5:20])
    sets = split1[1].split("; ")
    impossible = False
    for set in sets:
        cubes = set.split("\n")
        cubes = cubes[0].split(", ")
        for cube in cubes:
            cube = cube.split(" ")
            value = int(cube[0])
            color = cube[1]
            if(color == "red" and value > RED_CUBES):
                impossible = True
            if(color == "green" and value > GREEN_CUBES):
                impossible = True
            if(color == "blue" and value > BLUE_CUBES):
                impossible = True
    if(not(impossible)):
        sum = sum + id
    
print("First result: " + str(sum))


# SECOND PART

arch.seek(0)
sum = 0
for line in arch:
    split1 = line.split(": ")
    sets = split1[1].split("; ")
    minRed = 0
    minGreen = 0
    minBlue = 0
    for set in sets:
        cubes = set.split("\n")
        cubes = cubes[0].split(", ")
        for cube in cubes:
            cube = cube.split(" ")
            value = int(cube[0])
            color = cube[1]
            if(color == "red" and value > minRed):
                minRed = value
            if(color == "green" and value > minGreen):
                minGreen = value
            if(color == "blue" and value > minBlue):
                minBlue = value
    sum += (minRed * minBlue * minGreen)

print("Second result: " + str(sum))