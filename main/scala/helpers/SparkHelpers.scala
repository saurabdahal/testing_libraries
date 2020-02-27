package helpers

import java.io.IOException

import org.apache.spark.sql.{Dataset, Encoder, Row, SparkSession}
import org.apache.spark.sql.functions.{from_json, lit, schema_of_json}
import org.apache.spark.sql.types.TimestampType
import org.joda.time.LocalDate
import org.json.JSONObject
import spark.SparkPhoenixConnection


class SparkHelpers @throws[IOException]
() {
  private val spark = SparkSession.builder.master("local[6]").getOrCreate

  try {
    var name: String = "n/a"
    spark.udf.register("agent", (data: String) => {
      if (data != null) {
        var job: JSONObject = new JSONObject(data)
        if (job.get("AgentName") != null) {
          name = job.get("AgentName").toString
        }
      }
      name
    })
  } catch {
    case e: Exception =>
      e.printStackTrace()
      println(s"Exception thrown from ${e.getMessage}")

  }

  try {
    var calculation: Double = 0.0
    spark.udf.register("calculation", (data: String) => {
      if (data != null && data.length > 0) {
        var job: JSONObject = new JSONObject(data)
        calculation = job.get("AgentCommissonAmount").toString.toDouble
      }
      calculation
    })
  } catch {
    case e: Exception =>
      e.printStackTrace()
      println(s"Exception thrown from ${e.getMessage}")
  }

  try {
    var branch: String = ""
    spark.udf.register("branch", (data: String) => {
      if (data != null && data.length > 0) {
        var job: JSONObject = new JSONObject(data)
        branch = job.get("branchCode").toString
      }
      branch
    })
  } catch {
    case e: Exception =>
      e.printStackTrace()
      println(s"Exception thrown from ${e.getMessage}")
  }

  try {
    var quater: String = ""
    var mnth: Int = 0
    spark.udf.register("quater", (month: String) => {
      if (month != null && month.length > 0) {
        mnth = month.toInt
        if (mnth >= 1 && mnth <= 3) {
          quater = "01"
        } else if (mnth >= 4 && mnth <= 6) {
          quater = "02"
        } else if (mnth >= 7 && mnth <= 9) {
          quater = "03"
        } else if (mnth >= 10 && mnth <= 12) {
          quater = "04"
        }
      }
      quater
    })
  } catch {
    case e: Exception =>
      e.printStackTrace()
      println(s"Exception thrown from ${e.getMessage}")
  }

  try {
    var month_name: String = ""
    var dt: String = ""
    spark.udf.register("month_name", (data: String) => {
      if (data != null && data.length > 0) {
        dt = data.split(" ")(0)
        var date: LocalDate = new LocalDate(dt)
        if (date.isAfter(new LocalDate("2019-04-14")) && date.isBefore(new LocalDate("2019-05-14"))) {
          month_name = "Baisakh_10"
        } else if (date.isAfter(new LocalDate("2019-06-16")) && date.isBefore(new LocalDate("2019-07-16"))) {
          month_name = "Asar_12"
        } else if (date.isAfter(new LocalDate("2019-05-15")) && date.isBefore(new LocalDate("2019-06-15"))) {
          month_name = "Jestha_11"
        } else if (date.isAfter(new LocalDate("2019-07-16")) && date.isBefore(new LocalDate("2019-08-17"))) {
          month_name = "Shrawan_01"
        } else if (date.isAfter(new LocalDate("2020-04-13")) && date.isBefore(new LocalDate("2020-05-13"))) {
          month_name = "Shrawan_01"
        } else if (date.isAfter(new LocalDate("2020-05-14")) && date.isBefore(new LocalDate("2020-06-14"))) {
          month_name = "Jestha_11"
        } else if (date.isAfter(new LocalDate("2020-06-15")) && date.isBefore(new LocalDate("2020-07-15"))) {
          month_name = "Asar_12"
        } else if (date.isAfter(new LocalDate("2019-08-18")) && date.isBefore(new LocalDate("2019-10-17"))) {
          month_name = "Bhadra_02"
        } else if (date.isAfter(new LocalDate("2019-09-18")) && date.isBefore(new LocalDate("2019-10-17"))) {
          month_name = "Asoj_03"
        } else if (date.isAfter(new LocalDate("2019-10-18")) && date.isBefore(new LocalDate("2019-11-16"))) {
          month_name = "Kartik_04"
        } else if (date.isAfter(new LocalDate("2019-11-17")) && date.isBefore(new LocalDate("2019-12-16"))) {
          month_name = "Mangsir_05"
        } else if (date.isAfter(new LocalDate("2019-12-17")) && date.isBefore(new LocalDate("2020-01-14"))) {
          month_name = "Poush_06"
        } else if (date.isAfter(new LocalDate("2020-01-15")) && date.isBefore(new LocalDate("2020-02-12"))) {
          month_name = "Magh_07"
        } else if (date.isAfter(new LocalDate("2020-02-13")) && date.isBefore(new LocalDate("2020-03-13"))) {
          month_name = "Falgun_08"
        } else if (date.isAfter(new LocalDate("2020-03-14")) && date.isBefore(new LocalDate("2020-04-12"))) {
          month_name = "Chaitra_09"
        }
      }
      println(s"data = $data and month name = $month_name and dt = $dt")
      month_name
    })
  } catch {
    case e: Exception =>
      e.printStackTrace()
      println(s"Exception thrown from ${e.getMessage}")
  }

  def readFromPostgres(): Dataset[Row] = {
    import spark.implicits._

    val df = spark.read
      .format("jdbc")
      .option("url", s"jdbc:postgresql://10.10.5.61:32100/underwriting")
      .option("dbtable", "(select p.*,c.\"ClassDetailJSON\",c.\"CalulationDetailJSON\" CalculationDetailJSON " +
        "from \"PolicyIssuance\" p join \"ClassDetails\" c on p.\"Id\" = c.\"PolicyIssuanceId\" where p.\"FiscalYear\"='19/20') as tmp")
      //      .option("dbtable", "(select * from \"ClassDetails\") as tmp")
      .option("user", "postgres")
      .option("password", "Admin@123")
      .option("inferSchema", "true")
      .load()

    df.printSchema()
    df.createOrReplaceTempView("table")
    spark.catalog.cacheTable("table")
    val a = spark.sql("select *,agent(AgentDetail) agent,branch(BranchDetail) branch,calculation(CalculationDetailJSON) comission from table")
    a.coalesce(1).createOrReplaceTempView("a")
    spark.catalog.cacheTable("a")

    val b = spark.sql("select " +
      "Portfolio," +
      "split(IssueDate,' ')[0]," +
      "split(ExpiryDate,' ')[0]," +
      "split(EffectiveDate,' ')[0]," +
      "branch," +
      "Class," +
      "DocumentNumber," +
      "DocumentType Document_Type," +
      "EndorsementType," +
      "SumInsured," +
      "Transaction premium_amount," +
      "Stampduty," +
      "VatAmount," +
      "VATBillNo," +
      "comission," +
      "PolicyNumber," +
      "InsuredPartyName," +
      "quater(month(cast(IssueDate as TimeStamp))) quater," +
      "split(month_name(cast(IssueDate as String)),'_')[0] month_name," +
      "split(month_name(cast(IssueDate as String)),'_')[1] fiscal_month," +
      "agent,0 data from a")

    //    b.createOrReplaceTempView("c")
    //    b.show(20, truncate = 0)
    b
  }

  def read_from_csv(): Unit = {
    val df = spark.read.option("header", "true").option("inferSchema", "true").csv("/home/saurab/projects/olympic/athlete_events.csv")
    df.printSchema()
    df.na.fill(0)
    df.createOrReplaceTempView("ra")
    spark.sql("select ID,name,sex,cast(age as integer) age,cast(height as integer) height,cast(weight as integer) weight," +
      "team,NOC,games,year,season,city,sport,event,medal from ra").createOrReplaceTempView("table")

    spark.sql("select max(weight) from table").show()
  }
}
