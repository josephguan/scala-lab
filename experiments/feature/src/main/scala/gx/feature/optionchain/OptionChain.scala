package gx.feature.optionchain

/**
  * Option是一种Monad，
  * 可以使用flatMap把操作串起来，而不用操心其中某步失败。
  */
object OptionChain {

  case class Connection(connStr: String)

  case class User(email: Email)

  case class Email(sub: String)

  def getConnection(connStr: String): Option[Connection] = if (connStr == "mysql") Some(Connection(connStr)) else None

  def getUser(conn: Connection, name: String): Option[User] = if (name == "joe") Some(User(Email("joe@gmail.com"))) else None

  def getEmail(user: User): Option[Email] = Option(user.email)

  def getEmailQuery(connStr: String, name: String): String = {
    getConnection(connStr).
      flatMap(c => getUser(c, name)).
      flatMap(u => getEmail(u)).
      map(_.sub).
      getOrElse("null")
  }

  def getEmailQueryV2(connStr: String, name: String): String = {
    val optEmail = for {
      conn <- getConnection(connStr)
      user <- getUser(conn, name)
      email <- getEmail(user)
    } yield email.sub
    optEmail.getOrElse("null")
  }

}