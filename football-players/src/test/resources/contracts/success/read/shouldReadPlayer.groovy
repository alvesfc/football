package success.read

import org.springframework.cloud.contract.spec.Contract
import org.springframework.cloud.contract.spec.internal.HttpMethods
import org.springframework.cloud.contract.spec.internal.RegexPatterns

Contract.make {
    description 'Should retrieve player when submitting all valid information.'
    name 'shouldReadPlayer'
    label 'shouldReadPlayer'

    request {
        headers {
            contentType(applicationJsonUtf8())
        }
        method HttpMethods.HttpMethod.GET
        url value(consumer(regex("/players/${new RegexPatterns().uuid().toString()}")), producer('/players/d0dcaaff-cb80-4054-98b4-f4b11b99d360'))
    }

    response {
        status 200
        headers {
            contentType(applicationJsonUtf8())
        }
        body([
                "name"       : value(consumer('J. Arp'), producer(regex('^.{3,30}$'))),
                "fullName"   : value(consumer('Jann-Fiete Arp'), producer(optional(regex('^.{6,60}$')))),
                "nationality": value(consumer('Germany'), producer(regex('^.{3,30}$'))),
                "bornDate"   : value(consumer('2000-01-06'), producer(regex(new RegexPatterns().isoDate()))),
                "active"     : value(consumer('true'), producer(regex(new RegexPatterns().anyBoolean()))),
                "positions"  : [
                        value(consumer('ST'), producer(regex('^LW|ST|RW|LF|CF|RF|CAM|LM|CM|RM|CDM|LWB|LB|CB|RB|RWB|GK|\$')))
                ]
        ])
    }
}

