package fail.update

import org.springframework.cloud.contract.spec.Contract
import org.springframework.cloud.contract.spec.internal.HttpMethods
import org.springframework.cloud.contract.spec.internal.RegexPatterns

Contract.make {
    description 'Should not update player with invalid born date.'
    name 'shouldNotUpdatePlayerWithInvalidBornDate'
    label 'shouldNotUpdatePlayerWithInvalidBornDate'

    request {
        headers {
            contentType(applicationJson())
        }
        method HttpMethods.HttpMethod.PUT
        url value(consumer(regex('/players/[0-9]{1,18}')), producer('/players/1'))
        body([
                "name"       : value(consumer(regex('^.{3,30}$')), producer('D. Mbonabucya')),
                "fullName"   : value(consumer(optional(regex('^.{6,60}$'))), producer('Désiré Mbonabucya ')),
                "nationality": value(consumer(regex('^.{3,30}$')), producer('Rwanda')),
                //TODO corrigir a regex
                "bornDate"   : value(consumer(regex('^.{3,30}$')), producer('1984/07/14')),
                "positions"  : [
                        value(consumer(regex('^LW|ST|RW|LF|CF|RF|CAM|LM|CM|RM|CDM|LWB|LB|CB|RB|RWB|GK|\$')), producer('ST'))
                ]
        ])
    }

    response {
        status 400

        body([
                "errors": [
                        [
                            "code": "FIELD_IS_INVALID",
                            "message": "Field bornDate with value (1984/07/14) is invalid."
                        ]
                ]
        ])

        headers {
            contentType(applicationJson())
        }
    }
}


