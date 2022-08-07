package sample.adapter.recipient
import arch.ddd.Id
import sample.domain.recipient.Recipient
import sample.domain.slot.Slot
import sample.domain.util.{Age, EmailAddress, Location}
import software.amazon.awssdk.services.dynamodb.DynamoDbClient
import software.amazon.awssdk.services.dynamodb.model.{AttributeValue, GetItemRequest, PutItemRequest}

import java.net.URI
import java.time.LocalDateTime
import javax.inject.Inject
import scala.jdk.CollectionConverters.{IterableHasAsScala, MapHasAsJava, MapHasAsScala}

class DynamoDbRecipientAdapter @Inject() extends IRecipientAdapter {

  private val localstackDynamoDbUrl = URI.create("localhost:4566")

  private val ddb: DynamoDbClient = DynamoDbClient.builder().endpointOverride(localstackDynamoDbUrl).build()

  private val tableName = "VACCINATION_RESERVATION"

  private val slotPrimaryKeyPrefix = "slot#"

  override def load(recipientId: Id[Recipient]): Option[Recipient] = {
    val keyVal   = Recipient.primaryKeyPrefix + recipientId.value
    val keyToGet = Map("pk" -> AttributeValue.builder().s(keyVal).build())
    val request  = GetItemRequest.builder().key(keyToGet.asJava).tableName(tableName).build()

    val response = ddb.getItem(request)
    if (response.hasItem) {
      val items = response.item().asScala.toMap

      val recipient = new Recipient(
        id = Id[Recipient](items("pk").s().drop(Recipient.primaryKeyPrefix.length).toLong),
        email = EmailAddress(items("email").s()),
        firstName = items("first_name").s(),
        lastName = items("last_name").s(),
        age = Age(items("age").n().toInt),
        slots = items("slots")
          .l().asScala.map(_.m().asScala).map { item =>
            Slot(
              id = Id[Slot](item("pk").s().drop(slotPrimaryKeyPrefix.length).toLong),
              reservationDate = LocalDateTime.parse(item("reservation_date").s()),
              location = Location(item("location").s)
            )
          }.toSeq
      )
      Some(recipient)
    } else None
  }

  override def save(recipient: Recipient): Unit = {
    val request = PutItemRequest
      .builder()
      .tableName(tableName)
      .item(recipient.forDynamoDbMap.asJava)
      .build()

    ddb.putItem(request)
  }
}
