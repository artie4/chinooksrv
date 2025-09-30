package org.artie4.chinooksrv.config

import org.artie4.chinooksrv.mapper.ArtistMapper
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.ComponentScan

@TestConfiguration
@ComponentScan(basePackageClasses = [ArtistMapper::class])
class MapperTestConfig
