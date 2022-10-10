//
//  MovieViewModel.swift
//  iosApp
//
//  Created by 이동연 on 2022/10/10.
//  Copyright © 2022 orgName. All rights reserved.
//

import Foundation
import shared

class MovieViewModel {
    var hp = ViewModelHelper()
    @Published var movieList: [MovieItem] = []
    
    
    func fetchMovies(
        refresh: Bool = true,
        searchQuery: String = "아"
    ) {
        hp.getMovieList(refresh: refresh, searchQuery: searchQuery, completionHandler: {
            movies, error in
            print("log is ", movies!)
            self.movieList = movies ?? []
        } )
    }
}
