using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Amqp;

namespace Console_App.Consumer
{
    public class Consumer
    {

        public void consumer() {

            Address address = new Address("amqp://admin:admin@localhost:5672");
            Connection connection = new Connection(address);
            Session session = new Session(connection);
            ReceiverLink receiver = new ReceiverLink(session, "receiver-link", "q1");

            //Console.WriteLine("Receiver connected to broker.");
            Message message = receiver.Receive(System.TimeSpan.MinValue);
            //Console.WriteLine("Received " + message.GetBody<string>());
            receiver.Accept(message);

            receiver.Close();
            session.Close();
            connection.Close();
        }
            }
}
