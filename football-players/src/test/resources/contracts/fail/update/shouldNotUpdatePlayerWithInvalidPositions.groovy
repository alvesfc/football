package fail.update

import org.springframework.cloud.contract.spec.Contract
import org.springframework.cloud.contract.spec.internal.HttpMethods
import org.springframework.cloud.contract.spec.internal.RegexPatterns

Contract.make {
    description 'Should not update player whith invalid positions.'
    name 'shouldNotUpdatePlayerWithInvalidPositions'
    label 'shouldNotUpdatePlayerWithInvalidPositions'

    request {
        headers {
            contentType(applicationJson())
        }
        method HttpMethods.HttpMethod.PUT
        url value(consumer(regex('/players/[0-9]{1,18}')), producer('/players/1'))
        body([
                "name": value(consumer(regex(new RegexPatterns().nonBlank())), producer('Neymar')),
                "fullName"   : value(consumer(optional(regex(new RegexPatterns().nonBlank()))), producer('Neymar da Silva Santos Jr. ')),
                "nationality": value(consumer(regex(new RegexPatterns().nonBlank())), producer('Brazil')),
                "bornDate"   : value(consumer(regex(new RegexPatterns().isoDate())), producer('1992-05-02')),
                "positions"  : [
                        value(consumer(regex('^((?!^LW|ST|RW|LF|CF|RF|CAM|LM|CM|RM|CDM|LWB|LB|CB|RB|RWB|GK|\$).)*$')), producer('GOL'))
                ]
        ])
    }

    response {
        status 400

        body([
                "errors": [
                        [
                            "code": "FIELD_IS_INVALID",
                            "message": "Field positions with value ([GOL]) is invalid."
                        ]
                ]
        ])

        headers {
            contentType(applicationJson())
        }
    }
}


