#!/usr/bin/python
from pymongo import MongoClient
from matplotlib import pyplot as plt
import math
#from statistics import mode
from scipy import stats
import time
import numpy as np
import json




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
    energy2=[]
    ts2=[]

    for datum in flat.find({}, {'con': 1, 'TS':1}):
        month = int(time.strftime('%m', time.localtime(int(datum['TS']))))
        if month > 1:
            if int(datum['TS'])>tso:
                t.append(time.strftime('%Y-%m-%d %H:%M:%S', time.localtime(int(datum['TS']))))
                energy2.append(float(datum['con']))
                ts2.append(int(datum['TS']))
                #print(datum['Power'])

        # prin/t(t)
    # plt.xticks(t)
    median = np.median(energy2)
    #print(len(energy2)-1)
    energy3=[]

    for i in range(len(energy2)-1):
        if (energy2[i]>100*median) or (energy2[i]<0.01*median):
            1-1
            #print( energy2[i], "--" , median)
        else:
            #print("ok values", energy2[i])
            energy3.append(energy2[i])
    #print(energy3)
    plt.plot(range(len(energy3)),energy3)
    plt.xlabel("Readings")
    plt.ylabel("Meter Readings")
    #plt.show()
    filename = 'Graphs/'+flat_name+'.png'
    print(filename)
    plt.savefig(filename,bbox_inches='tight')
    plt.cla();
