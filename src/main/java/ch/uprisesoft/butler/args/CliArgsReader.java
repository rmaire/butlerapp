/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.uprisesoft.butler.args;

import java.util.Optional;
import java.util.Set;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

/**
 *
 * @author rmaire
 */
public class CliArgsReader implements ArgsReader {

    private Options options;
    private CommandLineParser parser;
    private CommandLine cmd;

    public CliArgsReader(String[] args, Set<String> optionsStrings) throws ParseException {

        parser = new DefaultParser();
        options = new Options();

        for (String optionName : optionsStrings) {
//            System.out.println("Loading option " + optionName);

            options.addOption(Option.builder()
                    .argName(optionName)
                    .hasArg()
                    .longOpt(optionName)
                    .required(false)
                    .build()
            );
        }

        cmd = parser.parse(options, args);
    }

    @Override
    public Optional<String> getArg(String argName) {
        return hasArg(argName) ? Optional.of(cmd.getOptionValue(argName)) : Optional.empty();
    }

    @Override
    public boolean hasArg(String argName) {

        for (Option a : cmd.getOptions()) {
            if (a.getLongOpt().equals(argName)) {
                return true;
            }
        }
        
        return false;

    }

}
