import sys
import random
import matplotlib.pyplot as plt

x=[]
y=[]

x.append(0)
y.append(0)



with open('input.txt','r') as f:
	for line in f:
    		linearr=line.split()
    		x.append(float(linearr[0]))
    		y.append(float(linearr[1]))

plt.plot(x,y)
del x[:]
del y[:]

with open('staircase.txt','r') as f:
	for line in f:
    		linearr=line.split()
    		x.append(float(linearr[0]))
    		y.append(float(linearr[1]))

plt.plot(x,y)
del x[:]
del y[:]
with open('output.txt','r') as f:
	for line in f:
    		linearr=line.split()
    		x.append(float(linearr[0]))
    		y.append(float(linearr[1]))

plt.plot(x,y)
del x[:]
del y[:]
plt.show()
