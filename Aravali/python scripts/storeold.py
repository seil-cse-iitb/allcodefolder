#!/usr/bin/python
from pymongo import MongoClient
import math
import time
import sys
import datetime
import numpy as np
from matplotlib import pyplot as plt

client = MongoClient('10.129.23.41:27018')
db = client.data


clientLocal = MongoClient('127.0.0.1:27017')
dbLocal = clientLocal.test4


energy = []
maxenergy = []
TS = []
name = []
tsHourOld = -1
tsDayOld = -1
tsMonthOld = -1
tsYearOld = -1
tsSecOld = -1
consumption = [0]
avgConsumption = []
avgConsumptionTS=[]
power = [0]
for flat_name in db.collection_names():
    if flat_name[:7] == 'aravali':
        flat = db[flat_name]
    else:
        continue
    getLastTS = 0
    try:
	#32 mb is max limit of search, so giving an error
        for datum in dbLocal[flat_name].find({}, {'_id': 0, 'TS': 1}).sort("TS",-1).limit(1):
            getLastTS = datum['TS']
    except:
        print("error" , sys.exc_info()[0])
    print("last ts for ", flat_name," is ",getLastTS)

    now = datetime.datetime.now()
    epochOfStartMonth = (datetime.datetime(now.year,now.month,1,0,0) - datetime.datetime(1970,1,1)).total_seconds()

    #delete all rows  except this month as we only need current month's data
    collectionLocal = dbLocal[flat_name]
    #collectionLocal.remove({'TS':{'$lt':epochOfStartMonth}})
    for datum in flat.find({"TS": {"$gt": getLastTS}}, {'FwdWh': 1, '_id': 0, 'TS': 1, 'W1': 1, 'W2': 1, 'W3': 1}).sort("TS",1):

        if math.isnan(datum['FwdWh']) or math.isnan(datum['W1']) or math.isnan(datum['W2']) or math.isnan(
                datum['W3']):
            continue
        else:
            #   check timestamp

            #print(datum['TS'])
            hour = int(time.strftime('%H', time.localtime(datum['TS'])))
            day = int(time.strftime('%d', time.localtime(datum['TS'])))
            month = int(time.strftime('%m', time.localtime(datum['TS'])))
            year = int(time.strftime('%Y', time.localtime(datum['TS'])))
            minute = int(time.strftime('%M', time.localtime(datum['TS'])))


            month2 = time.strftime('%b', time.localtime(datum['TS']))
            #print("minute ",minute )
            if int(month) > tsMonthOld:

                collectionLocal = dbLocal[flat_name+month2]
            else:
                collectionLocal = dbLocal[flat_name+month2]

            if int(hour) > tsHourOld or int(day) > tsDayOld or int(month) > tsMonthOld or int(year) > tsYearOld:
                #avgConsumption.append(np.mean(consumption))
                print("minute change",minute, "flat-" , flat_name )
                #print(year)
                #print(month)
                #print(day)
                #print(hour)

                t = datetime.datetime(year, month, day, hour, 0)
                ts = time.mktime(t.timetuple())
                #print(ts)
                #avgConsumptionTS.append(ts)
                tsHourOld = int(time.strftime('%H', time.localtime(datum['TS'])))
                tsDayOld = int(time.strftime('%d', time.localtime(datum['TS'])))
                tsMonthOld = int(time.strftime('%m', time.localtime(datum['TS'])))
                tsYearOld = int(time.strftime('%Y', time.localtime(datum['TS'])))

                medianofcon = np.median(consumption)
                medianofpower = np.median(power)
                result = collectionLocal.insert_one({"con":medianofcon, "TS":datum['TS'] , "Power": medianofpower})
                #deleting the previous values of consumption list as new hour has started
                consumption = []
                power = []
                #add first value in the list
                consumption.append(datum['FwdWh'])
                powerv = float(datum['W1'])+float(datum['W2'])+float(datum['W3'])
                power.append(powerv)
            else:
                consumption.append(datum['FwdWh'])
                powerv=float(datum['W1'])+float(datum['W2'])+float(datum['W3'])
                power.append(powerv)
