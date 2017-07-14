#!/usr/bin/python
import sys
from pymongo import MongoClient
from matplotlib import pyplot as plt
import math
#from statistics import mode
from scipy import stats
import time
import numpy as np
import json


first_arg = sys.argv[1]


client=MongoClient('127.0.0.1:27017')
db=client.test3
for flat_name in db.collection_names():

    if flat_name[:7]=='aravali':
    	flat=db[flat_name]
    else:
    	continue
    flat=db[flat_name]

    t=[]
    tso=0
    ts2=[0]*24
    energy2 = [0]*24
    for datum in flat.find({}, {'Power': 1, 'TS':1}):
        month = int(time.strftime('%m', time.localtime(int(datum['TS']))))
        day = int(time.strftime('%d', time.localtime(int(datum['TS']))))
        if month == int(first_arg) and day>12 and day<20:
            hour = int(time.strftime('%H', time.localtime(datum['TS'])))

            energy2[hour] = energy2[hour]+float(datum['Power'])
            ts2[hour] = ts2[hour]+1
                #print(datum['Power'])
    energy3=[0]*24
    print(energy2)
    for i in range(len(energy2)):
        energy3[i] = energy2[i]/ts2[i]

    plt.plot(range(len(energy3)),energy3)
    plt.xlabel("Time in hours(0-24)")
    plt.ylabel("Power(Watts)")
    #plt.show()
    filename = 'GraphsAvgPower/'+flat_name+'.png'
    print(filename)
    plt.savefig(filename,bbox_inches='tight')
    plt.cla();
