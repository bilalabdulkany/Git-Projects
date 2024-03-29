using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.Logging;
//DI, logging, settings
namespace Console_App
{

    public class GreetingService : IGreetingService
    {

        private readonly ILogger<GreetingService> _log;
        private readonly IConfiguration _config;

        public GreetingService(ILogger<GreetingService> log, IConfiguration config)
        {
            this._log = log;
            this._config = config;
        }
        public void Run()
        {

            for (int i = 0; i < _config.GetValue<int>("LoopTimes"); i++)
            {
                _log.LogInformation("Run number {number}", i);
            }
        }
    }
}
