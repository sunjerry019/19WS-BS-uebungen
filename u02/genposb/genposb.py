#!/usr/bin/env python3

A = ["A", "B", "C"]
B = ["D", "E"]
k = list("ABCDE")

limit = 5*4*3*2*1

def checkOK(arr):
	total = [A, B]
	for comd in total:
		previdx = -2
		for ele in comd:
			idx = arr.index(ele)
			if idx < previdx:
				return False
			else:
				previdx = idx
	return True

def EbeforeB(arr):
	return arr.index("E") < arr.index("B")


for one in range(5):	
	output = [k[one]]
	subset_one = k[0:one] + k[one+1:]
	# print("ss1", subset_one)
	
	for two in range(4):
		output += [subset_one[two]]
		subset_two = subset_one[0:two] + subset_one[two+1:]
		# print("ss2", subset_two)

		for three in range(3):
			output += [subset_two[three]]
			subset_three = subset_two[0:three] + subset_two[three+1:]
			# print("ss3", subset_three)

			for four in range(2):
				output += [subset_three[four]]
				subset_four = subset_three[0:four] + subset_three[four+1:]
				# print("ss4", subset_four)

				for five in range(1):
					output += [subset_four[five]]
					if checkOK(output): 
						print("".join(output) + (" ERROR" if EbeforeB(output) else ""))
					output.pop()

				output.pop()
			output.pop()
		output.pop()
	output.pop()
