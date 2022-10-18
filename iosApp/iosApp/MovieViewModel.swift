//
//  MovieViewModel.swift
//  iosApp
//
//  Created by 이동연 on 2022/10/10.
//  Copyright © 2022 orgName. All rights reserved.
//

import Foundation
import shared

extension ContentView {
    class MovieViewModel : ObservableObject {
        var hp = ViewModelHelper()
        @Published var text = "Loading..."
        @Published var movieItem: [MovieItem]? = nil
        func searchMovie(searchQuery: String) {
            hp.getMovieListUseCase.invoke(refresh: true, searchQuery: searchQuery) { list, error in
                DispatchQueue.main.async {
                    if let list = list {
                        self.text = list.description
                        self.movieItem = list
                    } else {
                        self.text = error?.localizedDescription ?? "error"
                    }
                }

            }
        }
        
        init() {
            searchMovie(searchQuery: "서울")
            
//            Greeting().searchMovies(keyword: "서울") { result, error in
//                DispatchQueue.main.async {
//                    if let result = result {
//                        self.text = result
//                    } else {
//                        self.text = error?.localizedDescription ?? "error"
//                    }
//                }
//
//            }
        }
    }
}

