package hello_world
import com.google.inject.{Inject, Singleton}
import io.swagger.annotations._
import play.api.http.Writeable
import play.api.i18n.{I18nSupport, MessagesApi}
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.libs.json.{JsValue, Json, Writes}
import play.api.mvc.{Action, Controller}

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util._


@Api(value = "/helloWorld")
@Singleton
class HelloWorldApp @Inject()(val messagesApi: MessagesApi) extends Controller with I18nSupport{

    //implicit def jsWriteable[A](implicit w: Writes[A], wjs: Writeable[String]): Writeable[A] =
    //  wjs.map(String(_))

    @ApiOperation(value = "Get something.",
      notes = "Returns something.",
      httpMethod = "GET",
      produces = "application/json")
    def getResult = Action{

      Ok("Nikita, go to sleep!")
    }

}
