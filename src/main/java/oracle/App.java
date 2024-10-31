package oracle;

import oracle.simulator.ParsedCommand;
import oracle.simulator.exceptions.InvalidCommandException;
import oracle.simulator.exceptions.InvalidSiteMapException;
import oracle.simulator.SiteCleanupSimulator;
import oracle.simulator.SiteCleanupSimulatorImpl;
import oracle.simulator.utils.FileUtil;

import java.io.IOException;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ) {
        if (args.length != 1) {
            System.out.println("Usage: java -jar simulator-project-1.0-SNAPSHOT.jar <sitemap-file-path>");
            return;
        }

        String filePath = args[0];
        char[][] siteMap;
        try {
            siteMap = FileUtil.parseFileToCharArray(filePath);
        } catch (IOException e) {
            System.out.println("Error reading site map file: " + e.getMessage());
            return;
        }

        SiteCleanupSimulator simulator = null;
        try {
            simulator = new SiteCleanupSimulatorImpl(siteMap);
        } catch (InvalidSiteMapException e) {
            throw new RuntimeException(e);
        }
        Scanner scanner = new Scanner(System.in);

        while (!simulator.shouldQuitSimulation()) {
            System.out.print("(l)eft, (r)ight, (a)dvance <n>, (q)uit: ");
            String input = scanner.nextLine().trim();
            try {
                ParsedCommand command = ParsedCommand.parse(input);
                simulator.executeCommand(command);
            } catch (InvalidCommandException e) {
                System.out.println("Invalid command: " + e.getMessage());
            }
        }

        simulator.printSimulationSummary();
        System.out.println( "Thank you for using the Aconex site clearing simulator." );
    }
}
