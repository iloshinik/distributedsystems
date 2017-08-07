# TCP Server Application

This project contains a simple TCP sever which is capable of handling multiple concurrent clients. 

The maximum number of clients serviceable at a given time is decided by the **MAX_POOL_SIZE** parameter.

As the number of simultaneous connections grows, the thread pool will automatically expand the number of threads up to the **MAX_POOL_SIZE**.

When the number of connections decreases, the thread pool will "kill" each spare thread upto **MAX_POOL_SIZE** after it has been sitting idle for some time which is decided by the **KEEP_ALIVE_TIME** parameter. 

When the maximum number of clients is reached, and new requests are received, such client requests will be queued for a period of time until a thread in the thread pool which handling a connection frees up.

Maximum queue size is defined by the **QUEUE_SIZE** parameter and when this parameter exceeds client requests won't accept and jobs aren't executed further.

The full queue situation is handled by catching **RejectedExecutionException**.



## How to run

Execute,
**sh
./startup.sh**


Clients are connected via **telnet localhost SERVER_PORT**.
