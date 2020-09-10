/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.uprisesoft.butler.atlasrunner;

import ch.uprisesoft.butler.plugin.api.Runner;
import ch.uprisesoft.butler.plugin.api.RunnerFactory;
import ch.uprisesoft.butler.plugin.api.Schema;
import org.pf4j.Extension;
import org.pf4j.Plugin;
import org.pf4j.PluginWrapper;

/**
 *
 * @author rmaire
 */
public class AtlasRunnerPlugin extends Plugin {

    public AtlasRunnerPlugin(PluginWrapper wrapper) {
        super(wrapper);
    }

    @Extension
    public static class AtlasRunnerFactory implements RunnerFactory {

        @Override
        public Runner getRunner() {
            return new AtlasRunner();
        }

        @Override
        public Schema getInputNames() {
            return new Schema()
                    .withText("Bundle Name", "bundle01")
                    .commit()
                    .withText("Domain", "zuara.io")
                    .commit()
                    .withPassword("Password", "")
                    .commit()
                    .withSelect("Product")
                    .addOption("Jira")
                    .addOption("Confluence")
                    .addOption("Bitbucket")
                    .addOption("Bamboo")
                    .commit()
                    .withText("Instance Hostname", "myhostname01")
                    .commit()
                    .withText("Max Memory", "1024m")
                    .commit();
        }


        @Override
        public Schema getOutputNames() {
            Schema schema = new Schema();
            
            return schema;
        }

        @Override
        public String getName() {
            return "atlasrunner";
        }
    }

}
