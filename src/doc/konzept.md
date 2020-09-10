# Konzept und Vorarbeiten für Butler

Das Ziel ist eine Software, um automatisierte Runbooks zur Verfügun zu stellen.
Andere Mitarbeiter sollen in Selbstbedienung z.B. neue Testinstanzen, Server usw. 
erstellen können. Die Runbooks sollen dabei parametrisierbar sein.

## Generell

## Komponenten

### Runner
Ein Runner ist ein Stück Code, dass wiederverwendbar einen Befehl o.Ä. ausführt.
Die Granularität entspricht dabei z.B. einem Shellscript, deinem File-Upload oder
einem Docker Start Befehl. Vorstellbar sind z.B. folgende Runner:

- Remote Shell
- Remote Powershell
- Local shell
- Docker
- Terraform
- Webservice Aufruf
- Forms Automator
- Lua Script

Ein Runner setzt sich zusammen aus Inputparametern, einem Template, dass bei 
Ausführung mit den Inputs angereichert wird und Outputs.

## Runbook

Die Runner sind zusammensetzbar zu Runbooks, die z.B. 


## Notizen
- Runner: Input -> Template -> Output. Der Output soll verarbeitbar sein.
- Parameter: Wie Variablenscope: Zuerst in runner, dann in Projekt, dann global suchen