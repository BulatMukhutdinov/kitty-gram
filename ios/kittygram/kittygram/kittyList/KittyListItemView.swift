import SwiftUI
import shared
import KingfisherSwiftUI
import Kingfisher

struct KittyListItemView: SwiftUI.View {
    
    private let options: KingfisherOptionsInfo = [.processor(DownsamplingImageProcessor(size: .init(width: 80, height: 80)))]
    
    let kitty: Kitty_
    
    var body: some SwiftUI.View {
        HStack {
            KFImage(URL(string: kitty.thumb), options: options)
                .resizable()
                .aspectRatio(contentMode: .fill)
                .frame(width: 80, height: 80)
                .padding()
            
            VStack(alignment: .leading, spacing: 4) {
                Text(kitty.breed)
                    .font(.system(size: 16, weight: .semibold))
                
                Text(kitty.about)
                    .lineLimit(3)
                    .font(.system(size: 14))
            }
        }
    }
}

struct KittyListItemView_Previews: PreviewProvider {
    
    static var previews: some  SwiftUI.View {
        KittyListItemView(kitty: Kitty_(thumb:"https://cdn.royalcanin-weshare-online.io/ZPpjHGsBIYfdNSoCVRpl/v1/bc13bl-hub-abyssinian-adult-standing?w=120&auto=compress&fm=jpg", icon: "https://cdn.royalcanin-weshare-online.io/lGljHGsBG95Xk-RBjPg2/v1/bc13h-hub-abyssinian-adult-black-and-white?w=480&auto=compress&fm=jpg", breed: "Абиссинская", about:"<i>Страна</i>: Эфиопия<br><i>Шерсть</i>: Короткошерстные породы<br><i>Размерная группа</i>: Средние", description: "Абиссинские кошки могут быть очень ласковыми, но только на своих условиях и по настроению. Они очень обаятельны, но могут быть чрезмерно озорными.<br><br>Абиссинская кошка игривая и активная, она хорошо подходит на роль товарища по играм. По мере взросления она крепко привязывается к людям и старается всегда находиться рядом с ними. Абиссинская кошка — общительная, энергичная, преданная и довольно общительная порода.")
        )
    }
}
