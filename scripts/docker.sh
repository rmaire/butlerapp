apt-get -y update
apt-get -y upgrade
apt-get -y install apt-transport-https ca-certificates curl gnupg-agent software-properties-common make dkms build-essential bsdtar wget git-core unzip sudo jq openjdk-8-jre

curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo apt-key add -
add-apt-repository "deb [arch=amd64] https://download.docker.com/linux/ubuntu $(lsb_release -cs) stable"

apt-get -y update
apt-get -y install docker-ce docker-ce-cli containerd.io

systemctl stop docker

echo "DOCKER_OPTS='-H tcp://0.0.0.0:4243 -H unix:///var/run/docker.sock'" | sudo tee --append /etc/environment

sed -i "s/ExecStart=\/usr\/bin\/dockerd -H fd:\/\//ExecStart=\/usr\/bin\/dockerd -H fd:\/\/ -H tcp:\/\/0.0.0.0:4243/" /lib/systemd/system/docker.service

#cat > /etc/docker/daemon.json <<EOL
#{
#  "storage-driver": "aufs"
#}
#EOL

usermod -aG docker vagrant

systemctl daemon-reload
systemctl start docker
systemctl enable docker
systemctl reload docker

wget https://releases.hashicorp.com/packer/1.4.0/packer_1.4.0_linux_amd64.zip
unzip packer_1.4.0_linux_amd64.zip -d /usr/bin/
chmod +x /usr/bin/packer

wget https://releases.hashicorp.com/consul/1.5.2/consul_1.5.2_linux_amd64.zip
unzip consul_1.5.2_linux_amd64.zip -d /usr/bin/
chmod +x /usr/bin/consul

wget https://releases.hashicorp.com/consul-template/0.20.0/consul-template_0.20.0_linux_amd64.zip
unzip consul-template_0.20.0_linux_amd64.zip -d /usr/bin/
chmod +x /usr/bin/consul-template

mkdir /etc/consul.d/

cat > /etc/consul.d/ui.json <<EOLUI
{
  "addresses": {
    "http": "0.0.0.0"
  }
}
EOLUI

cat > /etc/systemd/system/consul.service <<EOLSRV
[Unit]
Description=Consul
Documentation=https://www.consul.io/

[Service]
ExecStart=/usr/bin/consul agent -server -bootstrap-expect=1 -enable-script-checks=false -enable-local-script-checks=false -syslog -log-file /var/log/consul.log -ui -data-dir=/tmp/consul -node='mycloud.vm' -bind=10.3.5.20 -config-dir=/etc/consul.d/
ExecReload=/bin/kill -HUP \$MAINPID
LimitNOFILE=65536

[Install]
WantedBy=multi-user.target
EOLSRV

systemctl daemon-reload
systemctl start consul.service
systemctl enable consul.service
systemctl stop consul
systemctl start consul