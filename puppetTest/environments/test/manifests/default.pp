exec { 'apt-get update':
command => 'apt-get update',
path => $path,
}



node "web.lan"{


  package { "nginx":
  ensure => latest,
    require => Exec['apt-get update'],
}

service { 'nginx':
    ensure  => running,
    require => Package['nginx'],
  }
 
}

#   class { '::mysql::server':
#  package_name            => 'mysql-server',
#  package_ensure          => '5.7.1+mysql~trusty',
#  root_password           => 'strongpassword',
#  remove_default_accounts => true,
#}

 node "dbserver.lan"{

  include mysql
}
  #class {"cloudera":
  #  cm_server_host => 'node-00001.mycluster.lan',
  #  use_parcels => false,
  #  install_cmserver => true,
 #   install_lzo => true,
 #   db_type => "mysql",
 #   db_user => "root",
  #  db_pass => $mysql_admin_password
  #}


 # package { ['mysql-server']:
  #  ensure => present,
  #  require => Exec['apt-get update'],    
  #  #ensure => installed,
  #}

    # Run mysql
 # service { 'mysql':
 #   ensure  => running,
 #   require => Package['mysql-server'],
 # }



node "appserver.lan"{

#install curl
  package {'curl':
    require => Exec['apt-get update'],
	  ensure => latest,
  }

  package { "git":
    ensure => latest,
    require => Exec["apt-get update"]
  }

  exec { "git_clone_n":
     command => "git clone https://github.com/visionmedia/n.git /home/vagrant/n",
     path => ["/bin", "/usr/bin"],
     require => [Exec["apt-get update"], Package["git"], Package["curl"]]
     #, Package["g++"]
   }


   exec { "install_n":
     command => "make install",
     path => ["/bin", "/usr/bin"],
     cwd => "/home/vagrant/n",
     require => Exec["git_clone_n"]
   }

   exec { "install_node":
     command => "n stable",
     path => ["/bin", "/usr/bin", "/usr/local/bin"],
     require => [Exec["git_clone_n"], Exec["install_n"]]
   }

 }

 node "default"{

}

class mysql {

# Install mysql
package { ['mysql-server']:
ensure => present,
require => Exec['apt-get update'],
}

# Run mysql
service { 'mysql':
ensure => running,
require => Package['mysql-server'],
}

/*
# Use a custom mysql configuration file
file { '/etc/mysql/my.cnf':
source => 'puppet:///modules/mysql/my.cnf',
require => Package['mysql-server'],
notify => Service['mysql'],
}
*/

# We set the root password here
exec { 'set-mysql-password':
unless => 'mysqladmin -uroot -proot status',
command => "mysqladmin -uroot password filly",
path => ['/bin', '/usr/bin'],
require => Service['mysql'];
}
}
