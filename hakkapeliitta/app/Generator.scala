/**
  * Created by ian on 4/23/16.
  */


import com.github.tminglei.slickpg._
import play.api.libs.json.{Json, JsValue}
import slick.driver.JdbcProfile
import slick.profile.Capability

trait MyPostgresDriver extends ExPostgresDriver
  with PgArraySupport
  with PgDateSupport
  with PgRangeSupport
  with PgHStoreSupport
  with PgPlayJsonSupport
  with PgSearchSupport
  with PgNetSupport
  with PgLTreeSupport {
  def pgjson = "jsonb" // jsonb support is in postgres 9.4.0 onward; for 9.3.x use "json"

  // Add back `capabilities.insertOrUpdate` to enable native `upsert` support; for postgres 9.5+
  override protected def computeCapabilities: Set[Capability] =
    super.computeCapabilities + JdbcProfile.capabilities.insertOrUpdate

  override val api = MyAPI

  object MyAPI extends API with ArrayImplicits
    with DateTimeImplicits
    with JsonImplicits
    with NetImplicits
    with LTreeImplicits
    with RangeImplicits
    with HStoreImplicits
    with SearchImplicits
    with SearchAssistants {
    implicit val strListTypeMapper = new SimpleArrayJdbcType[String]("text").to(_.toList)
    implicit val playJsonArrayTypeMapper =
      new AdvancedArrayJdbcType[JsValue](pgjson,
        (s) => utils.SimpleArrayUtils.fromString[JsValue](Json.parse(_))(s).orNull,
        (v) => utils.SimpleArrayUtils.mkString[JsValue](_.toString())(v)
      ).to(_.toList)
  }
}

object MyPostgresDriver extends MyPostgresDriver

object Generator extends App {
  val outputDir = "/tmp/code3"
  val url = "jdbc:postgresql://localhost/worldcon75" // connection info
  val jdbcDriver = "org.postgresql.Driver"
  val slickDriver = "MyPostgresDriver"
  val pkg = "dao"

  slick.codegen.SourceCodeGenerator.main(
    Array(slickDriver, jdbcDriver, url, outputDir, pkg, "worldcon75", "changeme")
  )
}