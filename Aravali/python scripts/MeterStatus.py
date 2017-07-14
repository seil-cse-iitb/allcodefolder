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
import csv
import pandas
colnames = ['zeroto20','app','twentyto40','fortyto60','above60','higEngTime','weUsage','flatId','wdUsage','avgBill','autoApp']

data = pandas.read_csv('surveydata.csv', names=colnames)
flatids = data.flatId.tolist()


flat_id=[]
reading=[]
TS=[]

client=MongoClient('10.129.23.23:27017')
db=client.data
for flat_name in db.collection_names():
    if flat_name[:7]=='aravali':
    	flat=db[flat_name]
    else:
    	continue
    flat=db[flat_name]
    for datum in flat.find({}, {'FwdWh': 1, 'TS':1}).sort('TS',-1).limit(1):
        flat_id.append(flat_name)
        reading.append(datum['FwdWh'])
        TS.append(time.strftime('%Y-%m-%d %H:%M:%S', time.localtime(int(datum['TS']))))
# print(reading)
surveydone=[]
print(flat_id)
for i in range(len(flat_id)):


    fi = flat_id[i]
    print(fi," ",flat_id[i] )
    if fi[8:] in flatids:
        surveydone.append(1)
    else:
        surveydone.append(0)
    # print(flat_id[i],"   ",reading[i],"  ",TS[i])

with open('statusrep.csv', 'w') as csvfile:
    fieldnames = ['Meter_Id', 'Meter_Reading','TS','survey_done']
    writer = csv.DictWriter(csvfile, fieldnames=fieldnames)

    writer.writeheader()
    for i in range(len(flat_id)):
        writer.writerow({'Meter_Id': flat_id[i], 'Meter_Reading': reading[i],'TS':TS[i],'survey_done' : surveydone[i]})
