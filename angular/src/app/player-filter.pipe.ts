import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'playerFilter'
})
export class PlayerFilterPipe implements PipeTransform {

  transform(players: string[], playerFilter: string): string[] {
     if(!players || !playerFilter) {
      return players;
     }

    return players.filter(data =>
      data['username'].toLowerCase().indexOf(playerFilter.toLowerCase()) !== -1
    );
  }

}
