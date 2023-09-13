package com.baubuddy.v2.worker


//class NetworkWorker(appContext: Context, workerParams: WorkerParameters):
//    Worker(appContext, workerParams) {
//    @OptIn(DelicateCoroutinesApi::class)
//    override fun doWork(): Result {
//        // Do the work here--in this case, upload the images.
//        Log.d("Worker", " <<<NetworkWorker: Worker Start>>>")
//        try {
//            var taskDB: TaskDatabase
//            taskDB = TaskDatabase.getDatabase(applicationContext)
//            val data: ArrayList<Task> = GetDataFromTheServer().run()
//            GlobalScope.launch(Dispatchers.IO) {
//                if (data.isNotEmpty()){
//                    taskDB.taskDao().dropMyStoredData()
//                    for (i in data.indices){
//                        taskDB.taskDao().addTask(
//                            data[i]
//                        )
//                    }
//                }
//            }
//            Log.d("Worker", " <<<Worker Finished>>>")
////            val output = Data.Builder()
////                .putBoolean("STATUS", true)
////                .build()
//            return Result.success()
//        } catch (e: java.lang.Exception){
//            return Result.failure()
//        }
//    }
//}