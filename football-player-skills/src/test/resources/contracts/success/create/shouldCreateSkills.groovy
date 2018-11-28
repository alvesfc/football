package success.create

import org.springframework.cloud.contract.spec.Contract
import org.springframework.cloud.contract.spec.internal.HttpMethods
import org.springframework.cloud.contract.spec.internal.RegexPatterns

Contract.make {
    description 'Should create a skills for a player when submitting all valid information.'
    name 'shouldCreateSkills'
    label 'shouldCreateSkills'

    request {
        headers {
            contentType(applicationJson())
        }
        method HttpMethods.HttpMethod.POST
        url value(consumer(regex("/skills")), producer('/skills'))
        body([
                "playerID"     : value(consumer(regex(new RegexPatterns().uuid())), producer(anyUuid())),
                "season"       : value(consumer(regex('^.{3,30}$')), producer('2017-2018')),
                "traits"       : [
                        value(consumer(regex('^.{3,30}$')), producer('Diver')),
                        value(consumer(regex('^.{3,30}$')), producer('Long Shot Taker'))
                ],
                "specialities" : [
                        value(consumer(regex('^.{3,30}$')), producer('Dribbler'))
                ],
                "attributes"   : [
                        [
                                "category": value(consumer(regex('^.{3,30}$')), producer('Physical')),
                                "values"  : [
                                        [
                                                "name" : value(consumer(regex('^.{3,30}$')), producer('Acceleration')),
                                                "value": value(consumer(regex('[0-9]{1,2}')), producer(80)),

                                        ],
                                        [
                                                "name" : value(consumer(regex('^.{3,30}$')), producer('Agility')),
                                                "value": value(consumer(regex('[0-9]{1,2}')), producer(75)),

                                        ]
                                ]
                        ],
                        [
                                "category": value(consumer(regex('^.{3,30}$')), producer('Technical')),
                                "values"  : [
                                        [
                                                "name" : value(consumer(regex('^.{3,30}$')), producer('Ball Control')),
                                                "value": value(consumer(regex('[0-9]{1,2}')), producer(88)),

                                        ]
                                ]
                        ]
                ],
                'additionalInfo': [
                        "preferredFoot"          : value(consumer(regex('^.{3,30}$')), producer('Left')),
                        "internationalReputation": value(consumer(regex('[0-9]{1,2}')), producer(5)),
                        "weakFoot"               : value(consumer(regex('[0-9]{1,2}')), producer(5)),
                        "skillMoves"             : value(consumer(regex('[0-9]{1,2}')), producer(5)),
                        "workRate"               : value(consumer(regex('^.{3,30}$')), producer('Medium/Medium')),
                        "bodyType"               : value(consumer(regex('^.{3,30}$')), producer('Stocky')),
                        "realFace"               : value(consumer(regex(new RegexPatterns().anyBoolean())), producer(true)),
                        "releaseClause"          : value(consumer(regex('^.{3,30}$')), producer('€31.5M')),
                ]
        ])

    }

    response {
        status 201

        headers {//TODO Switch to regex
            header("location", regex("/skills/${new RegexPatterns().uuid()}"))
        }
    }
}


