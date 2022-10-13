//
//  MovieRow.swift
//  iosApp
//
//  Created by 이동연 on 2022/10/14.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import shared

struct MovieRow: View {
    var movieItem: MovieItem
    
    var body: some View {
        HStack {
            ZStack {
                if #available(iOS 15.0, *) {
                    AsyncImage(url: URL(string: movieItem.thumb ?? "https://example.com/icon.png")) { image in
                        image.centerCropped()
                    } placeholder: {
                        ProgressView()
                    }
                    .frame(height: 100)
                } else {
                    // Fallback on earlier versions
                }
            }
            
            VStack(alignment: .leading, spacing: 6) {
                Text(movieItem.title).font(.title).lineLimit(1)
                Text(movieItem.subTitle ?? "안녕하세용")
            }
        }
    }
}
extension Image {
    func centerCropped() -> some View {
        self
        .resizable()
        .scaledToFill()
        .frame(width: 100, height:200)
        .clipped()
    }
}
struct MovieRow_Previews: PreviewProvider {
    static var previews: some View {
        var sample = MovieItem(id: 1, title: "Sample Movie", subTitle: "This movie is for you", thumb: "http://img.cgv.co.kr/Movie/Thumbnail/Poster/000083/83821/83821_1000.jpg", director: nil, actor: nil, publishedAt: nil, rating: 0.5, link: nil)
        MovieRow(movieItem: sample)
            .previewLayout(.fixed(width: 300, height: 100))
    }
}
