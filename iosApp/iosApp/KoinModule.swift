//
//  KoinModule.swift
//  iosApp
//
//  Created by 이동연 on 2022/10/11.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import shared

func startKoin() {
    
    _koin = KoinKt.doInitKoin().koin
    
}

private var _koin: Koin_coreKoin?

var koin: Koin_coreKoin {
    return _koin!
}

extension Koin_coreKoin {
    func get() -> GetMovieListUseCase {
        
    }
}
