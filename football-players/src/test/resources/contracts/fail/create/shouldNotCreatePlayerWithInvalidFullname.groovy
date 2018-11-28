package fail.create

import org.springframework.cloud.contract.spec.Contract
import org.springframework.cloud.contract.spec.internal.HttpMethods
import org.springframework.cloud.contract.spec.internal.RegexPatterns

Contract.make {
    description 'Should not create player with invalid fullname.'
    name 'shouldNotCreatePlayerWithInvalidFullname'
    label 'shouldNotCreatePlayerWithInvalidFullname'

    request {
        headers {
            contentType(applicationJson())
        }
        method HttpMethods.HttpMethod.POST
        url value(consumer(regex('/players')), producer('/players'))
        body([
                "name"       : value(consumer(regex('^.{3,30}$')), producer('M. Sadler')),
                "fullName"   : value(consumer(optional(regex('^((?!^.{6,60}$).)*$'))), producer('Ma')),
                "nationality": value(consumer(regex('^.{3,30}$')), producer('England')),
                "bornDate"   : value(consumer(regex(new RegexPatterns().isoDate())), producer('1985-02-26')),
                "positions"  : [
                        value(consumer(regex('^LW|ST|RW|LF|CF|RF|CAM|LM|CM|RM|CDM|LWB|LB|CB|RB|RWB|GK|\$')), producer('GK'))
                ]
        ])
    }

    response {
        status 400

        body([
                "errors": [
                        [
                            "code": "FIELD_IS_INVALID",
                            "message": "Field fullName with value (Ma) is invalid."
                        ]
                ]
        ])

        headers {
            contentType(applicationJson())
        }
    }
}


