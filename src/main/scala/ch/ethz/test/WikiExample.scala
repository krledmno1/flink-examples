package ch.ethz.test


import org.apache.flink.streaming.api.scala._ /* to avoid imlicit TypeInformation problems */
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.streaming.api.windowing.time.Time
import org.apache.flink.streaming.connectors.wikiedits.WikipediaEditsSource


/**
  * Created by krle on 06.02.17.
  */
object WikiExample {
  def main(args: Array[String]) {



    // set up the streaming execution environment
    val env = StreamExecutionEnvironment.getExecutionEnvironment

    val stream = env.addSource(new WikipediaEditsSource())
      .keyBy(_.getUser)
      .timeWindow(Time.seconds(5))
      .fold(("",0L))((R,WE)=>(WE.getUser, R._2+WE.getByteDiff))
      //.sum(1)
      .print()

    // execute program
    env.execute("Flink Streaming Wiki Edits Example")
  }
}
