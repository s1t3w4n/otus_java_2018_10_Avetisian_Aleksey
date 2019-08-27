package runner;

import app.ProcessRunner;
import messageSystem.Address;
import server.SocketMessageServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ProcessRunnerImpl implements ProcessRunner {

    private final StringBuffer out = new StringBuffer();
    private final SocketMessageServer server;
    private Process process;

    public ProcessRunnerImpl(SocketMessageServer server) throws IOException {
        this.server = server;
    }


    @Override
    public void start(String command) throws IOException {
        process = runProcess(command);
    }

    @Override
    public void stop() {
        process.destroy();
    }

    @Override
    public String getOutput() {
        return out.toString();
    }

    private Process runProcess(String command) throws IOException {
        ProcessBuilder pb = new ProcessBuilder(command.split(" "))
                .redirectOutput(ProcessBuilder.Redirect.INHERIT);

        pb.redirectErrorStream(true);
        Process p = pb.start();

        StreamListener output = new StreamListener(p.getInputStream(), "OUTPUT");
        server.registerClientSocket(new Address(Integer.valueOf(command.split(" ")[0]).toString()));
        output.start();

        return p;
    }

    private class StreamListener extends Thread {
        //private final Logger logger = Logger.getLogger(StreamListener.class.getName());

        private final InputStream is;
        private final String type;

        private StreamListener(InputStream is, String type) {
            this.is = is;
            this.type = type;
        }

        @Override
        public void run() {
            try (InputStreamReader isr = new InputStreamReader(is)) {
                BufferedReader br = new BufferedReader(isr);
                String line;
                while ((line = br.readLine()) != null) {
                    out.append(type).append('>').append(line).append('\n');
                }
            } catch (IOException e) {
                //logger.log(Level.SEVERE, e.getMessage());
            }
        }
    }
}
