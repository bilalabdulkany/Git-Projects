using System;
using Amqp;

namespace Console_App.Publisher
{
    public class PublisherClass
    {

        private readonly string brokerUri;

        public PublisherClass()
        {
            
        }

        public void publish(string msg) {
            Address address = new Address("amqp://localhost:5672");
            Connection connection = new Connection(address);
            Session session = new Session(connection);

            Message message = new Message("Hello AMQP!");
            SenderLink sender = new SenderLink(session, "sender-link", "q1");
            sender.Send(message);
            

            sender.Close();
            session.Close();
            connection.Close();
        }


    }
}
