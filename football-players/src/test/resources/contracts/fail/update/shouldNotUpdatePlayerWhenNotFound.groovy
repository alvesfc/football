package fail.update

import org.springframework.cloud.contract.spec.Contract
import org.springframework.cloud.contract.spec.internal.HttpMethods
import org.springframework.cloud.contract.spec.internal.RegexPatterns

Contract.make {
    description 'Should not update player when not find in to database'
    name 'shouldNotUpdatePlayerWhenNotFound'
    label 'shouldNotUpdatePlayerWhenNotFound'

    request {
        headers {
            contentType(applicationJsonUtf8())
        }
        method HttpMethods.HttpMethod.PUT
        url value(consumer(regex("/players/${new RegexPatterns().uuid().toString()}")),producer('/players/ab74980b-28cd-44b2-8cc0-c51ff0a336d8'))
        body([
                "name"       : value(consumer(optional(regex('^.{3,30}$'))), producer('Arp')),
                "fullName"   : value(consumer(optional(regex('^.{6,60}$'))), producer('Jann-Fiete Arp')),
                "nationality": value(consumer(optional(regex('^.{3,30}$'))), producer('Germany')),
                "bornDate"   : value(consumer(optional(regex(new RegexPatterns().isoDate()))), producer('2000-01-06')),
                "positions"  : [
                        value(consumer(optional(regex('^LW|ST|RW|LF|CF|RF|CAM|LM|CM|RM|CDM|LWB|LB|CB|RB|RWB|GK|\$'))), producer('ST'))
                ]
        ])
    }

    response {
        status 404

        body([
                "errors": [
                        [
                                "code": "PLAYER_NOT_FOUND",
                                "message": "Player not found."
                        ]
                ]
        ])

        headers {
            contentType(applicationJsonUtf8())
        }
    }
}
