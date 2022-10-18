//
//  LazyInject.swift
//  iosApp
//
//  Created by 이동연 on 2022/10/11.
//  Copyright © 2022 orgName. All rights reserved.
//

@propertyWrapper
struct LazyInject<T> {
    private lazy var value: T = {
        let appDelegate = IO.shared.delegate as! AppDelegate
        return appDelegate.container.resolve(T.self)!
    }()
    
    var wrappedValue: T {
        mutating get { return value }
    }
    
}
