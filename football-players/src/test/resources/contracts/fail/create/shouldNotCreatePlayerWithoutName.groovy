package fail.create

import org.springframework.cloud.contract.spec.Contract
import org.springframework.cloud.contract.spec.internal.HttpMethods
import org.springframework.cloud.contract.spec.internal.RegexPatterns

Contract.make {
    description 'Should not create player whithout name.'
    name 'shouldNotCreatePlayerWithoutName'
    label 'shouldNotCreatePlayerWithoutName'

    request {
        headers {
            contentType(applicationJson())
        }
        method HttpMethods.HttpMethod.POST
        url value(consumer(regex('/players')), producer('/players'))
        body([
                "fullName"   : value(consumer(optional(regex(new RegexPatterns().nonBlank()))), producer('Neymar da Silva Santos Jr. ')),
                "nationality": value(consumer(regex(new RegexPatterns().nonBlank())), producer('Brazil')),
                "bornDate"   : value(consumer(regex(new RegexPatterns().isoDate())), producer('1992-05-02')),
                "positions"  : [
                        value(consumer(regex('^LW|ST|RW|LF|CF|RF|CAM|LM|CM|RM|CDM|LWB|LB|CB|RB|RWB|GK|\$')), producer('LW'))
                ]
        ])

    }

    response {
        status 400

        body([
                "errors": [
                        [
                            "code": "FIELD_IS_MANDATORY",
                            "message": "The field name is required."
                        ]
                ]
        ])

        headers {
            contentType(applicationJson())
        }
    }
}


