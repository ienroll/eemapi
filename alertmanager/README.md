#URL
  
  https://www.unixdaemon.net/sysadmin/adding-alert-manager-docker-compose-prometheus/
  https://medium.com/@abhishekbhardwaj510/alertmanager-integration-in-prometheus-197e03bfabdf
  
# AlertManager Server

Run a local alertmanager server and configure the prometheus container to send alerts to it.
  
  docker-compose -f alertmanager/docker-compose.yaml up -d

## Useful commands

View running config

    docker exec -ti promdocker_alert-manager_1 amtool --alertmanager.url http://127.0.0.1:9093 config

View active alerts

    docker exec -ti promdocker_alert-manager_1 amtool --alertmanager.url http://127.0.0.1:9093 alert

    Alertname  Starts At                Summary
    RedisDown  2018-03-04 22:23:25 UTC  Redis Availability alert.


# Alert manager configuration

https://prometheus.io/docs/alerting/configuration/#%3Croute%3E

# webhook_config for alertmanager-webhook-servicenow

The webhook receiver allows configuring a generic receiver.

## Whether or not to notify about resolved alerts.
` [ send_resolved: <boolean> | default = true ] `

## The endpoint to send HTTP POST requests to.
`url: <string>`

## The HTTP client's configuration.
  [ http_config: <http_config> | default = global.http_config ]
  The Alertmanager will send HTTP POST requests in the following JSON format to the configured endpoint:
  
  {
    "version": "4",
    "groupKey": <string>,    // key identifying the group of alerts (e.g. to deduplicate)
    "status": "<resolved|firing>",
    "receiver": <string>,
    "groupLabels": <object>,
    "commonLabels": <object>,
    "commonAnnotations": <object>,
    "externalURL": <string>,  // backlink to the Alertmanager.
    "alerts": [
      {
        "status": "<resolved|firing>",
        "labels": <object>,
        "annotations": <object>,
        "startsAt": "<rfc3339>",
        "endsAt": "<rfc3339>",
        "generatorURL": <string> // identifies the entity that caused the alert
      },
      ...
    ]
  }
  There is a list of integrations with this feature - https://prometheus.io/docs/operating/integrations/#alertmanager-webhook-receiver
