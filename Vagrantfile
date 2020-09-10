# https://github.com/vagrant-landrush/landrush/blob/master/doc/Usage.adoc#visibility-on-the-host
# vagrant plugin install landrush
# netsh interface ip show config
# netsh interface ipv4 add dnsserver "VirtualBox Host-Only Network #13" address=127.0.0.1 index=1

Vagrant.configure("2") do |config|

    config.landrush.enabled = true
    config.landrush.tld = 'vm'

    config.vm.define "linux" do |linux|
        linux.vm.box = "bento/ubuntu-18.04"
        #linux.vm.network "forwarded_port", guest: 4243, host: 4243
        linux.vm.network "private_network", ip: "10.3.5.20"
        linux.vm.hostname = "mycloud.vm"
        linux.ssh.insert_key = false
        linux.ssh.private_key_path = ["key/private/id_test", "~/.vagrant.d/insecure_private_key"]
  
        linux.vm.provider "virtualbox" do |v|
            v.memory = 2048
            v.cpus = 2
            #v.gui = true
        end

        linux.vm.provision "file", source: "key/public/id_test.pub", destination: "~/.ssh/authorized_keys"
        linux.vm.provision "shell", path: "scripts/docker.sh"

    end

    config.vm.define "windows" do |windows|
        windows.vm.box = "mwrock/Windows2012R2"
        windows.vm.network "private_network", ip: "10.3.5.30"
        windows.vm.provider "virtualbox" do |v|
            v.memory = 2048
            v.cpus = 2
            v.gui = true
        end        
    end

end