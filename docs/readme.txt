LABS	SUPPORTING FILES
Lab 1: SimpleDB 
Lab 2: SimpleDB operators 	 nsf_data.tar.gz 
Lab 3: SimpleDB transactions 	
Lab 4: Query optimization 
Lab 5: Rollback and recovery 

Patch (TXT)


H:2,block:4,blockSize:1000,chunk:2,clean:1,created:17ca18188e7,format:1,version:2,fletcher:b5e179df

H:2,block:4,blockSize:1000,chunk:2,clean:1,created:17ca18188e7,format:1,version:2,fletcher:b5e179df


chunk:1,block:2,len:2,map:1,max:1180,next:4,pages:12,root:400003e184,time:18,version:1,pinCount:0  
map.1	name.dataroot.1	name:data1
4000002d4d
chunk:1,block:2,version:1,fletcher:84d0d5f6 


chunk:2,block:4,len:1,map:1,max:580,next:5,pages:7,root:80000109cc,time:33,version:2,pinCount:0 

chunk.1map.1	name.dataroot.1?
chunk:1,block:2,len:2,liveMax:b80,livePages:b,map:1,max:1180,next:4,pages:12,root:400003e184,time:18,unusedAtVersion:1,version:1,pinCount:0	name:data

chunk:2,block:4,version:2,fletcher:9bd0d9f6  


FileChannel 的使用
porting 
straightforward recursive-descent design.
intermediate 
mechanism 



数据 int类型 4bytes*3字段
4096 bytes*8
定长 可变长

heapFile包括n个heapPage
header设计很简单，page内容就是BufferPool.PAGE_SIZE - # header bytes

每个page存放的 tuple 数量：
一个page有4096 bytes/
tuple*8+1

:: nrecords::337::: nheaderbytes ::::43::: nheaderbits :::344

header就是  tupsPerPage/8





