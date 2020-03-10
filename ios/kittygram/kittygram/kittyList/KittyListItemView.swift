import SwiftUI
import shared
import KingfisherSwiftUI
import Kingfisher

struct KittyListItemView: SwiftUI.View {
    
    private let options: KingfisherOptionsInfo = [.processor(DownsamplingImageProcessor(size: .init(width: 80, height: 80)))]
    
    let kitty: Kitty
    
    var body: some SwiftUI.View {
        HStack {
            KFImage(URL(string: kitty.icon), options: options)
                .resizable()
                .aspectRatio(contentMode: .fill)
                .frame(width: 80, height: 80)
            
            VStack(alignment: .leading, spacing: 4) {
                Text(kitty.breed)
                    .font(.system(size: 16, weight: .semibold))
                
                Text(kitty.component3())
                    .lineLimit(3)
                    .font(.system(size: 14))
                
            }
        }
    }
}

struct KittyListItemView_Previews: PreviewProvider {
    
    static var previews: some  SwiftUI.View {
        KittyListItemView(kitty: Kitty(icon: "https://www.royal-canin.ru/upload/iblock/a9c/glavnaya.jpg", breed: "Абиссинская", description: "Как правило, по своему характеру абиссинские кошки весьма активны, они стремятся принимать участие во всех домашних делах. Могут быть очень ласковы, но только на своих условиях и по настроению. Необыкновенно обаятельны, но иногда бывают очень озорными. Эти игривые, подвижные создания хорошо уживутся с любым другим домашним животным, которое сможет стать им товарищем по играм. Они сильно привязываются к людям и стараются всегда находиться рядом с ними. Все эти особенности характера и поведения абиссинской кошки не дадут ее хозяину заскучать или почувствовать себя одиноким."))
    }
}
