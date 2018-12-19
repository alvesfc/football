package fail.update

import org.springframework.cloud.contract.spec.Contract
import org.springframework.cloud.contract.spec.internal.HttpMethods
import org.springframework.cloud.contract.spec.internal.RegexPatterns

Contract.make {
    description 'Should not update player with invalid nationality.'
    name 'shouldNotUpdatePlayerWithInvalidNationality'
    label 'shouldNotUpdatePlayerWithInvalidNationality'

    request {
        headers {
            contentType(applicationJsonUtf8())
        }
        method HttpMethods.HttpMethod.PUT
        url value(consumer(regex("/players/${new RegexPatterns().uuid().toString()}")),producer('/players/ab74980b-28cd-44b2-8cc0-c51ff0a336d8'))
        body([
                "name"       : value(consumer(regex('^.{3,30}$')), producer('S. Handanovič')),
                "fullName"   : value(consumer(optional(regex('^.{6,60}$'))), producer('Samir Handanovič ')),
                "nationality": value(consumer(regex('^((?!^.{3,30}$).)*$')), producer('Slovenia Slovenia Slovenia Slovenia')),
                "bornDate"   : value(consumer(regex(new RegexPatterns().isoDate())), producer('1984-07-14')),
                "positions"  : [
                        value(consumer(regex('^LW|ST|RW|LF|CF|RF|CAM|LM|CM|RM|CDM|LWB|LB|CB|RB|RWB|GK|\$')), producer('CB')),
                        value(consumer(regex('^LW|ST|RW|LF|CF|RF|CAM|LM|CM|RM|CDM|LWB|LB|CB|RB|RWB|GK|\$')), producer('LB'))

                ]
        ])
    }

    response {
        status 400

        body([
                "errors": [
                        [
                            "code": "FIELD_IS_INVALID",
                            "message": "Field nationality with value (Slovenia Slovenia Slovenia Slovenia) is invalid."
                        ]
                ]
        ])

        headers {
            contentType(applicationJsonUtf8())
        }
    }
}


