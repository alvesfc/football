package success.update

import org.springframework.cloud.contract.spec.Contract
import org.springframework.cloud.contract.spec.internal.HttpMethods
import org.springframework.cloud.contract.spec.internal.RegexPatterns

Contract.make {
    description 'Should update player when submitting all valid information.'
    name 'shouldUpdatePlayer'
    label 'shouldUpdatePlayer'

    request {
        headers {
            contentType(applicationJsonUtf8())
        }
        method HttpMethods.HttpMethod.PUT
        url value(consumer(regex("/players/${new RegexPatterns().uuid().toString()}")), producer('/players/0a74c042-7d65-40a7-ae0e-4cb8e6d2bda6'))

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
        status 200
    }
}


