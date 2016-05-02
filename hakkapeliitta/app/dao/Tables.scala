package dao

import play.api.libs.json.JsValue

// AUTO-GENERATED Slick data model
/** Stand-alone Slick data model for immediate use */
object Tables extends {
  val profile = MyPostgresDriver
} with Tables

/** Slick data model trait for extension, choice of backend or usage in the cake pattern. (Make sure to initialize this late.) */
trait Tables {
  val profile: slick.driver.JdbcProfile
  import profile.api._
  import slick.model.ForeignKeyAction
  // NOTE: GetResult mappers for plain SQL are only generated for tables where Slick knows how to map the types of all columns.
  import slick.jdbc.{GetResult => GR}

  /** DDL for all tables. Call .create to execute. */
  lazy val schema: profile.SchemaDescription = Admins.schema ++ Paperpublications.schema ++ People.schema ++ Transactions.schema
  @deprecated("Use .schema instead of .ddl", "3.0")
  def ddl = schema

  /** Entity class storing rows of table Admins
 *
   *  @param id Database column id SqlType(int4), PrimaryKey
   *  @param peopleId Database column people_id SqlType(int4)
   *  @param superAdmin Database column super_admin SqlType(bool), Default(false)
   *  @param memberAdmin Database column member_admin SqlType(bool), Default(false)
   *  @param hugoAdmin Database column hugo_admin SqlType(bool), Default(false) */
  case class AdminsRow(id: Int, peopleId: Int, superAdmin: Boolean = false, memberAdmin: Boolean = false, hugoAdmin: Boolean = false)
  /** GetResult implicit for fetching AdminsRow objects using plain SQL queries */
  implicit def GetResultAdminsRow(implicit e0: GR[Int], e1: GR[Boolean]): GR[AdminsRow] = GR{
    prs => import prs._
    AdminsRow.tupled((<<[Int], <<[Int], <<[Boolean], <<[Boolean], <<[Boolean]))
  }
  /** Table description of table admins. Objects of this class serve as prototypes for rows in queries. */
  class Admins(_tableTag: Tag) extends Table[AdminsRow](_tableTag, "admins") {
    def * = (id, peopleId, superAdmin, memberAdmin, hugoAdmin) <> (AdminsRow.tupled, AdminsRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), Rep.Some(peopleId), Rep.Some(superAdmin), Rep.Some(memberAdmin), Rep.Some(hugoAdmin)).shaped.<>({r=>import r._; _1.map(_=> AdminsRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(int4), PrimaryKey */
    val id: Rep[Int] = column[Int]("id", O.PrimaryKey)
    /** Database column people_id SqlType(int4) */
    val peopleId: Rep[Int] = column[Int]("people_id")
    /** Database column super_admin SqlType(bool), Default(false) */
    val superAdmin: Rep[Boolean] = column[Boolean]("super_admin", O.Default(false))
    /** Database column member_admin SqlType(bool), Default(false) */
    val memberAdmin: Rep[Boolean] = column[Boolean]("member_admin", O.Default(false))
    /** Database column hugo_admin SqlType(bool), Default(false) */
    val hugoAdmin: Rep[Boolean] = column[Boolean]("hugo_admin", O.Default(false))

    /** Foreign key referencing People (database name admins_people_id_fkey) */
    lazy val peopleFk = foreignKey("admins_people_id_fkey", peopleId, People)(r => r.id, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.NoAction)
  }
  /** Collection-like TableQuery object for table Admins */
  lazy val Admins = new TableQuery(tag => new Admins(tag))

  /** Entity class storing rows of table Paperpublications
 *
   *  @param id Database column id SqlType(int4), PrimaryKey
   *  @param peopleId Database column people_id SqlType(int4), Default(None)
   *  @param name Database column name SqlType(text), Default(None)
   *  @param address Database column address SqlType(text), Default(None)
   *  @param country Database column country SqlType(text), Default(None) */
  case class PaperpublicationsRow(id: Int, peopleId: Option[Int] = None, name: Option[String] = None, address: Option[String] = None, country: Option[String] = None)
  /** GetResult implicit for fetching PaperpublicationsRow objects using plain SQL queries */
  implicit def GetResultPaperpublicationsRow(implicit e0: GR[Int], e1: GR[Option[Int]], e2: GR[Option[String]]): GR[PaperpublicationsRow] = GR{
    prs => import prs._
    PaperpublicationsRow.tupled((<<[Int], <<?[Int], <<?[String], <<?[String], <<?[String]))
  }
  /** Table description of table paperpublications. Objects of this class serve as prototypes for rows in queries. */
  class Paperpublications(_tableTag: Tag) extends Table[PaperpublicationsRow](_tableTag, "paperpublications") {
    def * = (id, peopleId, name, address, country) <> (PaperpublicationsRow.tupled, PaperpublicationsRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), peopleId, name, address, country).shaped.<>({r=>import r._; _1.map(_=> PaperpublicationsRow.tupled((_1.get, _2, _3, _4, _5)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(int4), PrimaryKey */
    val id: Rep[Int] = column[Int]("id", O.PrimaryKey)
    /** Database column people_id SqlType(int4), Default(None) */
    val peopleId: Rep[Option[Int]] = column[Option[Int]]("people_id", O.Default(None))
    /** Database column name SqlType(text), Default(None) */
    val name: Rep[Option[String]] = column[Option[String]]("name", O.Default(None))
    /** Database column address SqlType(text), Default(None) */
    val address: Rep[Option[String]] = column[Option[String]]("address", O.Default(None))
    /** Database column country SqlType(text), Default(None) */
    val country: Rep[Option[String]] = column[Option[String]]("country", O.Default(None))

    /** Foreign key referencing People (database name paperpublications_people_id_fkey) */
    lazy val peopleFk = foreignKey("paperpublications_people_id_fkey", peopleId, People)(r => Rep.Some(r.id), onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.NoAction)
  }
  /** Collection-like TableQuery object for table Paperpublications */
  lazy val Paperpublications = new TableQuery(tag => new Paperpublications(tag))

  /** Entity class storing rows of table People
 *
   *  @param id Database column id SqlType(int4), PrimaryKey
   *  @param memberNumber Database column member_number SqlType(int4)
   *  @param firstName Database column first_name SqlType(text)
   *  @param lastName Database column last_name SqlType(text)
   *  @param badgeName Database column badge_name SqlType(text), Default(None)
   *  @param email Database column email SqlType(text), Default(None)
   *  @param city Database column city SqlType(text), Default(None)
   *  @param state Database column state SqlType(text), Default(None)
   *  @param country Database column country SqlType(text), Default(None)
   *  @param controllerId Database column controller_id SqlType(int4), Default(None)
   *  @param membership Database column membership SqlType(membershipstatus)
   *  @param canHugoNominate Database column can_hugo_nominate SqlType(bool), Default(false)
   *  @param canHugoVote Database column can_hugo_vote SqlType(bool), Default(false)
   *  @param canSiteSelect Database column can_site_select SqlType(bool), Default(false) */
  case class PeopleRow(id: Int, memberNumber: Int, firstName: String, lastName: String, badgeName: Option[String] = None, email: Option[String] = None, city: Option[String] = None, state: Option[String] = None, country: Option[String] = None, controllerId: Option[Int] = None, membership: String, canHugoNominate: Boolean = false, canHugoVote: Boolean = false, canSiteSelect: Boolean = false)
  /** GetResult implicit for fetching PeopleRow objects using plain SQL queries */
  implicit def GetResultPeopleRow(implicit e0: GR[Int], e1: GR[String], e2: GR[Option[String]], e3: GR[Option[Int]], e4: GR[Boolean]): GR[PeopleRow] = GR{
    prs => import prs._
    PeopleRow.tupled((<<[Int], <<[Int], <<[String], <<[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[Int], <<[String], <<[Boolean], <<[Boolean], <<[Boolean]))
  }
  /** Table description of table people. Objects of this class serve as prototypes for rows in queries. */
  class People(_tableTag: Tag) extends Table[PeopleRow](_tableTag, "people") {
    def * = (id, memberNumber, firstName, lastName, badgeName, email, city, state, country, controllerId, membership, canHugoNominate, canHugoVote, canSiteSelect) <> (PeopleRow.tupled, PeopleRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), Rep.Some(memberNumber), Rep.Some(firstName), Rep.Some(lastName), badgeName, email, city, state, country, controllerId, Rep.Some(membership), Rep.Some(canHugoNominate), Rep.Some(canHugoVote), Rep.Some(canSiteSelect)).shaped.<>({r=>import r._; _1.map(_=> PeopleRow.tupled((_1.get, _2.get, _3.get, _4.get, _5, _6, _7, _8, _9, _10, _11.get, _12.get, _13.get, _14.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(int4), PrimaryKey */
    val id: Rep[Int] = column[Int]("id", O.PrimaryKey)
    /** Database column member_number SqlType(int4) */
    val memberNumber: Rep[Int] = column[Int]("member_number")
    /** Database column first_name SqlType(text) */
    val firstName: Rep[String] = column[String]("first_name")
    /** Database column last_name SqlType(text) */
    val lastName: Rep[String] = column[String]("last_name")
    /** Database column badge_name SqlType(text), Default(None) */
    val badgeName: Rep[Option[String]] = column[Option[String]]("badge_name", O.Default(None))
    /** Database column email SqlType(text), Default(None) */
    val email: Rep[Option[String]] = column[Option[String]]("email", O.Default(None))
    /** Database column city SqlType(text), Default(None) */
    val city: Rep[Option[String]] = column[Option[String]]("city", O.Default(None))
    /** Database column state SqlType(text), Default(None) */
    val state: Rep[Option[String]] = column[Option[String]]("state", O.Default(None))
    /** Database column country SqlType(text), Default(None) */
    val country: Rep[Option[String]] = column[Option[String]]("country", O.Default(None))
    /** Database column controller_id SqlType(int4), Default(None) */
    val controllerId: Rep[Option[Int]] = column[Option[Int]]("controller_id", O.Default(None))
    /** Database column membership SqlType(membershipstatus) */
    val membership: Rep[String] = column[String]("membership")
    /** Database column can_hugo_nominate SqlType(bool), Default(false) */
    val canHugoNominate: Rep[Boolean] = column[Boolean]("can_hugo_nominate", O.Default(false))
    /** Database column can_hugo_vote SqlType(bool), Default(false) */
    val canHugoVote: Rep[Boolean] = column[Boolean]("can_hugo_vote", O.Default(false))
    /** Database column can_site_select SqlType(bool), Default(false) */
    val canSiteSelect: Rep[Boolean] = column[Boolean]("can_site_select", O.Default(false))
  }
  /** Collection-like TableQuery object for table People */
  lazy val People = new TableQuery(tag => new People(tag))

  /** Entity class storing rows of table Transactions
 *
   *  @param id Database column id SqlType(int4), PrimaryKey
   *  @param targetPeopleId Database column target_people_id SqlType(int4)
   *  @param authorPeopleId Database column author_people_id SqlType(int4)
   *  @param timestamp Database column timestamp SqlType(timestamptz)
   *  @param authorSource Database column author_source SqlType(text)
   *  @param sum Database column sum SqlType(money), Default(None)
   *  @param currency Database column currency SqlType(bpchar), Length(3,false), Default(None)
   *  @param membership Database column membership SqlType(membershipstatus), Default(None)
   *  @param canHugoNominate Database column can_hugo_nominate SqlType(bool), Default(None)
   *  @param canHugoVote Database column can_hugo_vote SqlType(bool), Default(None)
   *  @param canSiteSelect Database column can_site_select SqlType(bool), Default(None)
   *  @param action Database column action SqlType(text)
   *  @param parameters Database column parameters SqlType(jsonb), Length(2147483647,false)
   *  @param description Database column description SqlType(text) */
  case class TransactionsRow(id: Int, targetPeopleId: Int, authorPeopleId: Int, timestamp: java.sql.Timestamp, authorSource: String, sum: Option[Double] = None, currency: Option[String] = None, membership: Option[String] = None, canHugoNominate: Option[Boolean] = None, canHugoVote: Option[Boolean] = None, canSiteSelect: Option[Boolean] = None, action: String, parameters: String, description: String)
  /** GetResult implicit for fetching TransactionsRow objects using plain SQL queries */
  implicit def GetResultTransactionsRow(implicit e0: GR[Int], e1: GR[java.sql.Timestamp], e2: GR[String], e3: GR[Option[Double]], e4: GR[Option[String]], e5: GR[Option[Boolean]]): GR[TransactionsRow] = GR{
    prs => import prs._
    TransactionsRow.tupled((<<[Int], <<[Int], <<[Int], <<[java.sql.Timestamp], <<[String], <<?[Double], <<?[String], <<?[String], <<?[Boolean], <<?[Boolean], <<?[Boolean], <<[String], <<[String], <<[String]))
  }
  /** Table description of table transactions. Objects of this class serve as prototypes for rows in queries. */
  class Transactions(_tableTag: Tag) extends Table[TransactionsRow](_tableTag, "transactions") {
    def * = (id, targetPeopleId, authorPeopleId, timestamp, authorSource, sum, currency, membership, canHugoNominate, canHugoVote, canSiteSelect, action, parameters, description) <> (TransactionsRow.tupled, TransactionsRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), Rep.Some(targetPeopleId), Rep.Some(authorPeopleId), Rep.Some(timestamp), Rep.Some(authorSource), sum, currency, membership, canHugoNominate, canHugoVote, canSiteSelect, Rep.Some(action), Rep.Some(parameters), Rep.Some(description)).shaped.<>({r=>import r._; _1.map(_=> TransactionsRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get, _6, _7, _8, _9, _10, _11, _12.get, _13.get, _14.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(int4), PrimaryKey */
    val id: Rep[Int] = column[Int]("id", O.PrimaryKey)
    /** Database column target_people_id SqlType(int4) */
    val targetPeopleId: Rep[Int] = column[Int]("target_people_id")
    /** Database column author_people_id SqlType(int4) */
    val authorPeopleId: Rep[Int] = column[Int]("author_people_id")
    /** Database column timestamp SqlType(timestamptz) */
    val timestamp: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("timestamp")
    /** Database column author_source SqlType(text) */
    val authorSource: Rep[String] = column[String]("author_source")
    /** Database column sum SqlType(money), Default(None) */
    val sum: Rep[Option[Double]] = column[Option[Double]]("sum", O.Default(None))
    /** Database column currency SqlType(bpchar), Length(3,false), Default(None) */
    val currency: Rep[Option[String]] = column[Option[String]]("currency", O.Length(3,varying=false), O.Default(None))
    /** Database column membership SqlType(membershipstatus), Default(None) */
    val membership: Rep[Option[String]] = column[Option[String]]("membership", O.Default(None))
    /** Database column can_hugo_nominate SqlType(bool), Default(None) */
    val canHugoNominate: Rep[Option[Boolean]] = column[Option[Boolean]]("can_hugo_nominate", O.Default(None))
    /** Database column can_hugo_vote SqlType(bool), Default(None) */
    val canHugoVote: Rep[Option[Boolean]] = column[Option[Boolean]]("can_hugo_vote", O.Default(None))
    /** Database column can_site_select SqlType(bool), Default(None) */
    val canSiteSelect: Rep[Option[Boolean]] = column[Option[Boolean]]("can_site_select", O.Default(None))
    /** Database column action SqlType(text) */
    val action: Rep[String] = column[String]("action")
    /** Database column parameters SqlType(jsonb), Length(2147483647,false) */
    val parameters: Rep[JsValue] = column[JsValue]("parameters")
    /** Database column description SqlType(text) */
    val description: Rep[String] = column[String]("description")

    /** Foreign key referencing People (database name transactions_author_people_id_fkey) */
    lazy val peopleFk1 = foreignKey("transactions_author_people_id_fkey", authorPeopleId, People)(r => r.id, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.NoAction)
    /** Foreign key referencing People (database name transactions_target_people_id_fkey) */
    lazy val peopleFk2 = foreignKey("transactions_target_people_id_fkey", targetPeopleId, People)(r => r.id, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.NoAction)
  }
  /** Collection-like TableQuery object for table Transactions */
  lazy val Transactions = new TableQuery(tag => new Transactions(tag))
}
