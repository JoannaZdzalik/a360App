****** WILDFLY CONFIGURATION ********

File: standalone.xml

#1 deploy configuration

 <deployment name="ear-1.0-SNAPSHOT" runtime-name="ear-1.0-SNAPSHOT.ear">
            <fs-exploded path="C:\Users\LukaszStepien\IdeaProjects\team-beta\ear\target\ear-1.0-SNAPSHOT"/>
        </deployment>

  *change path to your path for deploy server

  #2 email deploy settings

          <subsystem xmlns="urn:jboss:domain:mail:3.0">
              <mail-session name="default" jndi-name="java:/mail" from="gienek@gmail.com">
                  <smtp-server outbound-socket-binding-ref="mail-smtp"  username=" " password=" "/>
              </mail-session>
          </subsystem>


  #3
          <outbound-socket-binding name="mail-smtp">
              <remote-destination host="localhost" port="25"/>
          </outbound-socket-binding>


